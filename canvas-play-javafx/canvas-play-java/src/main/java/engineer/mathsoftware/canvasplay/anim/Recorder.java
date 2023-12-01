// Copyright (c) 2023 Tobias Briones. All rights reserved.
// SPDX-License-Identifier: BSD-3-Clause
// This file is part of https://github.com/tobiasbriones/canvas-play

package engineer.mathsoftware.canvasplay.anim;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.canvas.Canvas;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static engineer.mathsoftware.canvasplay.utils.IOUtils.deleteDirRecursive;

public class Recorder {
    private static final String RECORDING_DIR = "recording";
    private static final int INITIAL_THREAD_NUM = 60;
    private final Canvas canvas;
    private final int targetFps;
    private final List<Thread> threads;

    public Recorder(Canvas canvas, int targetFps) {
        this.canvas = canvas;
        this.targetFps = targetFps;
        this.threads = Collections.synchronizedList(new ArrayList<>(INITIAL_THREAD_NUM));
    }

    public void saveSnapshot(int i) throws IOException {
        saveSnapshot(i, RECORDING_DIR);
    }

    public void saveSnapshot(int i, String dir) throws IOException {
        var shot = canvas.snapshot(null, null);
        var file = Paths
            .get("out", dir, "screenshot-" + i + ".png")
            .toFile();
        var par = file.getParentFile();

        if (i <= 1) {
            deleteDirRecursive(par);
        }

        if (!par.exists()) {
            par.mkdirs();
        }

        var t = Thread.startVirtualThread(() -> {
            try {
                ImageIO.write(
                    SwingFXUtils.fromFXImage(shot, null),
                    "png",
                    file
                );
                System.out.println("Screenshot saved to: " + file.getAbsolutePath());
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                threads.remove(Thread.currentThread());
            }
        });

        threads.add(t);
    }

    public void compileVideo() throws IOException, InterruptedException {
        waitForThreads();

        var outputPath = Path.of("out/recording.mp4");

        if (Files.exists(outputPath)) {
            Files.delete(outputPath);
        }
        var builder = new ProcessBuilder(
            "out/ffmpeg",
            "-framerate",
            String.valueOf(targetFps),
            "-i",
            "out/recording/screenshot-%d.png",
            "-c:v",
            "libx264",
            "-pix_fmt",
            "yuv420p",
            outputPath
                .toAbsolutePath()
                .toString()
        ).redirectErrorStream(true)
            .start();

        try (
            var r = new BufferedReader(
                new InputStreamReader(
                    builder.getInputStream(),
                    Charset.defaultCharset()
                )
            )
        ) {
            String line;

            while ((line = r.readLine()) != null) {
                System.out.println(line);
            }
            var exitCode = builder.waitFor();

            System.out.println("Command exited with code " + exitCode);
        }
    }

    void waitForThreads() throws InterruptedException {
        // Avoid deadlock by copying the current running threads and wait
        // for them separately
        List<Thread> threadsCopy;

        synchronized (threads) {
            threadsCopy = new ArrayList<>(threads);
        }

        for (var t : threadsCopy) {
            t.join();
        }
    }
}

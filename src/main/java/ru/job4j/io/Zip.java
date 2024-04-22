package ru.job4j.io;

import java.io.*;
import java.nio.file.Paths;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import java.nio.file.Path;

public class Zip {

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path onePath : sources) {
                File oneFile = onePath.toFile();
                zip.putNextEntry(new ZipEntry(oneFile.getPath()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(oneFile))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void argumentValidation(ArgsName arguments) {
        if (arguments.getSize() < 3) {
            throw new IllegalArgumentException("Not enough arguments. Should be 3.");
        }
        if (!new File(arguments.get("d")).exists()) {
            throw new IllegalArgumentException("This directory does not exist.");
        }
        if (!arguments.get("e").matches("^\\.[a-z0-9]{2,5}$")) {
            throw new IllegalArgumentException("The extension is not in the correct format.");
        }
        if (!arguments.get("o").endsWith(".zip")) {
            throw new IllegalArgumentException("The archive name must have the extension .zip");
        }
    }

    public static void main(String[] args) throws IOException {
        ArgsName argsToLaunchProgram = ArgsName.of(args);
        Zip zippingManyFiles = new Zip();
        zippingManyFiles.argumentValidation(argsToLaunchProgram);
        List<Path> filesWillBeInZip = Search.search(Paths.get(argsToLaunchProgram.get("d")),
                path -> !path.toFile().getName().endsWith(argsToLaunchProgram.get("e")));
        zippingManyFiles.packFiles(filesWillBeInZip, new File(argsToLaunchProgram.get("o")));
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
    }
}

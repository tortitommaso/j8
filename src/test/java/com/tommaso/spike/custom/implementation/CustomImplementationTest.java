package com.tommaso.spike.custom.implementation;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

interface BufferedReaderProcessor {
    String process(BufferedReader b) throws IOException;
}

public class CustomImplementationTest {

    private File _file;

    @Before
    public void createFile() throws IOException {
        _file = File.createTempFile("prefix", "suffix");
        Path path = Paths.get(_file.getAbsolutePath());
        try (BufferedWriter writer = Files.newBufferedWriter(path)) {
            writer.write("aaa\n");
            writer.write("bbb");
        }
    }

    @After
    public void deleteFile() {
        _file.delete();
    }

    @Test
    public void example() throws Exception {
        assertEquals("aaa", processFile());
        assertEquals("aaa", processFile((BufferedReader br) -> br.readLine()));
        assertEquals("aaabbb", processFile((BufferedReader br) -> br.readLine() + br.readLine()));
    }

    public String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(_file))) {
            return br.readLine();
        }
    }

    public String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(_file))) {
            return p.process(br);
        }
    }
}

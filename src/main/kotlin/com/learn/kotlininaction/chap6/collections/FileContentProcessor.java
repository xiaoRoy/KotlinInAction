package com.learn.kotlininaction.chap6.collections;

import java.io.File;
import java.util.List;

public interface FileContentProcessor {
    void processContents(File path, byte[] binaryContent, List<String> textContents);
}

package com.karthik.acc.service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public interface BatchService {
    public File removeNonPerformingAccounts(File file) throws IOException;
}

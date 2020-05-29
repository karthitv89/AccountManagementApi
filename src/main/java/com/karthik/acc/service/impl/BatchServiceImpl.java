package com.karthik.acc.service.impl;

import com.karthik.acc.service.BatchService;
import com.karthik.acc.service.ExclusionAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("batchService")
public class BatchServiceImpl implements BatchService {

    @Autowired
    private ExclusionAccountService exclusionAccountService;


    @Override
    public File removeNonPerformingAccounts(File file) throws IOException {

        List<String> accountsInFile = new ArrayList<>();
        File tempFile = new File("tmp_" + file.getName());

        boolean isHeader = true;

        List<String> accountsToBeExcluded = exclusionAccountService.listAllAsStringList();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
             BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile)))
        {
            String account;
            while ((account = br.readLine()) != null) {
                if (isHeader) {
                    isHeader = false;
                    writer.write(account + System.getProperty("line.separator"));
                    continue;
                } else {
                    String finalAccount = account;
                    Optional<String> optional = accountsToBeExcluded.stream().filter(e -> finalAccount.equals(e)).findFirst();
                    if (!optional.isPresent()) {
                        writer.write(account + System.getProperty("line.separator"));
                    }
                }
            }
        } finally {
            file.delete();
            tempFile.renameTo(file);
        }
        System.out.println(accountsInFile);
        return file;
    }

    private void removeNonPerfromingAssets() {

    }
}

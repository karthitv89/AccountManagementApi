package com.karthik.acc.controller;

import com.karthik.acc.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping("/batch")
public class ApplicationBatchController {

    @Autowired
    private BatchService batchService;
    @Autowired
    private HttpServletResponse response;

    @PostMapping(value = "/removeNPAccounts", produces = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void removeNonPerformingAccounts(@RequestParam("file") MultipartFile inputFile/*,
                                            RedirectAttributes    redirectAttributes*/) throws IOException {
        if (!inputFile.isEmpty()) {
            File uploadDirectory = new File(System.getProperty("java.io.tmpdir"));
            String orgName = inputFile.getOriginalFilename();
            String filePath = uploadDirectory +  File.separator + orgName;
            File dest = new File(filePath);
            File output;
                inputFile.transferTo(dest);
                output = batchService.removeNonPerformingAccounts(dest);

            byte[] fileContent = Files.readAllBytes(output.toPath());
            // get your file as InputStream
            InputStream is = new ByteArrayInputStream(fileContent);
            // copy it to response’s OutputStream
            FileCopyUtils.copy(is, response.getOutputStream());
            response.setContentType("plain/text");
            response.setHeader("Content-Disposition", "attachment;filename="+ orgName);
            response.flushBuffer();
        }
    }



}

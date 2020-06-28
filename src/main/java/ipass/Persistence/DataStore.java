package ipass.Persistence;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.common.StorageSharedKeyCredential;

import java.io.*;

public class DataStore {
    private  String data;
    DataStore manager = new DataStore();
public void setData(String input){
    data = input;
}


    public static File createTempFile() throws IOException {
        DataStore manager = new DataStore();
        File sampleFile = null;
        sampleFile = File.createTempFile("sampleFile", ".txt");
        Writer output = new BufferedWriter(new FileWriter(sampleFile));
        output.write(manager.data);
        output.close();
        return sampleFile;
    }

    public static void runData() throws IOException {




        File sampleFile = null;
        sampleFile = createTempFile();
        String downloadedFilePath = "downloadedFile.txt";


        String accountName = "ipassdamsteegproductions";
        String accountKey = "sC57POmTnNk6poPi07FxCnRTj9RNnBKBZlb+6fSzPVSgVnWZO08Yh1pj1ZiE4l90t0IOHDC7TUu+3E6y5vFgXQ==";
        String endpoint = "https://" + accountName + ".blob.core.windows.net";
        String containerName = "ipasscontainer";

        String blobName = "myblob";


        StorageSharedKeyCredential credential = new StorageSharedKeyCredential(accountName, accountKey);
       

        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
            .endpoint(endpoint)
            .credential(credential)
            .buildClient();



        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(containerName);

        if(!blobServiceClient.getBlobContainerClient(containerName).exists()) {
            blobServiceClient.createBlobContainer(containerName);
            System.out.printf("Creating a container : %s %n", blobContainerClient.getBlobContainerUrl());
        }else{
            System.out.println("container bestaat al");
        }

        BlobClient blobClient = blobContainerClient.getBlobClient(blobName);


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {

                System.out.println("Uploading the sample file into the container from a file: "
                        + blobContainerClient.getBlobContainerUrl());
                blobClient.uploadFromFile(sampleFile.toPath().toString(), true);
                break;

        }
    }
}

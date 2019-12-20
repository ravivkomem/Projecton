package entities;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.Serializable;

@SuppressWarnings("serial")
public class MyFile implements Serializable {

	private String Description = null;
	private String fileName = null;
	private int size = 0;
	public byte[] mybytearray;

	public void initArray(int size) {
		mybytearray = new byte[size];
	}

	public MyFile(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public byte[] getMybytearray() {
		return mybytearray;
	}

	public byte getMybytearray(int i) {
		return mybytearray[i];
	}

	public void setMybytearray(byte[] mybytearray) {

		for (int i = 0; i < mybytearray.length; i++)
			this.mybytearray[i] = mybytearray[i];
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public static MyFile parseToMyFile(String path) {
		MyFile myFile = new MyFile(path);
		String LocalfilePath = path;
		try {

			File newFile = new File(LocalfilePath);

			byte[] mybytearray = new byte[(int) newFile.length()];
			FileInputStream fis = new FileInputStream(newFile);
			BufferedInputStream bis = new BufferedInputStream(fis);

			myFile.initArray(mybytearray.length);
			myFile.setSize(mybytearray.length);

			bis.read(myFile.getMybytearray(), 0, mybytearray.length);
			//sendToServer(msg);
		} catch (Exception e) {
			System.out.println("Error send (Files)msg) to Server");
		}
		
		return myFile;
	}
}

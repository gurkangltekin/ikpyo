package sample;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class DosyaYazma {
    private String yazilacakDeger;
    private String dosyadanOkunanDeger;
    private String dosyadaAranacak;
    private Boolean dosyaKontrol;

    //private File file;
    //private FileWriter fw;
//    private BufferedWriter bw;
//    private FileReader fr;
//    private BufferedReader br;


	public void dosyaGuncelle(String str, List<String> lines) throws IOException {
		String path = Paths.get("").toAbsolutePath().toString();
		int size = lines.size();
		for ( int i = 0 ; i < size ; i++ ) {
			if ( i == 0 ){
				File file = new File(path + "\\src\\dosyalar\\" + str + ".txt");
				if(!file.exists()){
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, false);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(lines.get(i));
				bw.newLine();
				bw.close();
			}else{
				File file = new File(path + "\\src\\dosyalar\\" + str + ".txt");
				if(!file.exists()){
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, true);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(lines.get(i));
				bw.newLine();
				bw.close();
			}
		}
	}

    public List<String> dosyadanOku(String str) throws Exception {
		String path = Paths.get("").toAbsolutePath().toString();
    	File file = new File(path + "\\src\\dosyalar\\" + str + ".txt");
		FileReader fileReader = new FileReader(file);
		String line;

		List<String> lines = new ArrayList<>();

		BufferedReader br = new BufferedReader(fileReader);
		while ((line = br.readLine()) != null) {
			lines.add(line);
		}

		br.close();

    	return lines;
    }

    public void dosyayaYaz(String string) throws IOException {
		String path = Paths.get("").toAbsolutePath().toString();
		File file = new File(path + "\\src\\dosyalar\\" + string + ".txt");
		if(!file.exists()){
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(this.getYazilacakDeger());
		bw.newLine();
		bw.close();
    }

    public boolean dosyaAra(String fName) {
		String path = Paths.get("").toAbsolutePath().toString();
		boolean check = false;
		File f = new File(path + "\\src\\images\\" );
		File[] matchingFiles = f.listFiles(new FilenameFilter() {
			public boolean accept(File dir, String name) {
				return name.endsWith(".jpg");
			}
		});
		int size = matchingFiles.length;
		for(int i = 0 ; i < size ; i++){
			if(matchingFiles[i].getName().equals(fName)){
				check = true;
				i = size;
			}
		}
        return check;
    }

	public String getYazilacakDeger() {
		return yazilacakDeger;
	}

	public void setYazilacakDeger(String yazilacakDeger) {
		this.yazilacakDeger = yazilacakDeger;
	}

	public String getDosyadanOkunanDeger() {
		return dosyadanOkunanDeger;
	}

	public void setDosyadanOkunanDeger(String dosyadanOkunanDeger) {
		this.dosyadanOkunanDeger = dosyadanOkunanDeger;
	}

	public String getDosyadaAranacak() {
		return dosyadaAranacak;
	}

	public void setDosyadaAranacak(String dosyadaAranacak) {
		this.dosyadaAranacak = dosyadaAranacak;
	}

	public Boolean getDosyaKontrol() {
		return dosyaKontrol;
	}

	public void setDosyaKontrol(Boolean dosyaKontrol) {
		this.dosyaKontrol = dosyaKontrol;
	}

	/*public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}*/

//	public FileWriter getFw() {
//		return fw;
//	}
//
//	public void setFw(FileWriter fw) {
//		this.fw = fw;
//	}
//
//	public BufferedWriter getBw() {
//		return bw;
//	}
//
//	public void setBw(BufferedWriter bw) {
//		this.bw = bw;
//	}
//
//	public FileReader getFr() {
//		return fr;
//	}
//
//	public void setFr(FileReader fr) {
//		this.fr = fr;
//	}
//
//	public BufferedReader getBr() {
//		return br;
//	}
//
//	public void setBr(BufferedReader br) {
//		this.br = br;
//	}
}

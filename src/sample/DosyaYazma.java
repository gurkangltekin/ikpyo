package sample;

import java.io.*;
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
		int size = lines.size();
		for ( int i = 0 ; i < size ; i++ ) {
			if ( i == 0 ){
				File file = new File(str + ".txt");
				if(!file.exists()){
					file.createNewFile();
				}
				FileWriter fw = new FileWriter(file, false);
				BufferedWriter bw = new BufferedWriter(fw);
				bw.write(lines.get(i));
				bw.newLine();
				bw.close();
			}else{
				File file = new File(str + ".txt");
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
    	File file = new File(str + ".txt");
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
		File file = new File(string + ".txt");
		if(!file.exists()){
			file.createNewFile();
		}
		FileWriter fw = new FileWriter(file, true);
		BufferedWriter bw = new BufferedWriter(fw);
		bw.write(this.getYazilacakDeger());
		bw.newLine();
		bw.close();
    }

    public String dosyadaAra() {
        return null;
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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Map;

public class Pass2 {

	public static void main(String[] args) {

		try {
			
			String f = "IC.txt";
			FileReader fw = new FileReader(f);
			BufferedReader IC_file = new BufferedReader(fw);

			String f1 = "SYMTAB.txt";
			FileReader fw1 = new FileReader(f1);
			BufferedReader symtab_file = new BufferedReader(fw1);
			symtab_file.mark(500);

			String f2 = "LITTAB.txt";
			FileReader fw2 = new FileReader(f2);
			BufferedReader littab_file = new BufferedReader(fw2);
			littab_file.mark(500);

			String littab[][]=new String[10][2] ;
			
			Hashtable<String, String> symtab = new Hashtable<String, String>();
			String str;
			int z=0;
			while ((str = littab_file.readLine()) != null) {

				littab[z][0]=str.split("\t")[0];
				littab[z][1]=str.split("\t")[1];
				z++;
			}
			while ((str = symtab_file.readLine()) != null) {
				symtab.put(str.split("\t")[0], str.split("\t")[1]);
			}

			
			String f4 = "MACHINE_CODE.txt";
			FileWriter fw4 = new FileWriter(f4);
			BufferedWriter machine_code_file = new BufferedWriter(fw4);

			String sCurrentLine;
			sCurrentLine = IC_file.readLine();
			int locptr=0;
			locptr=Integer.parseInt(sCurrentLine.split("\t")[3]);
			
			while ((sCurrentLine = IC_file.readLine()) != null) {
				
				machine_code_file.write(locptr+"\t");		//always write the LC
				
				
				String s0 = sCurrentLine.split("\t")[0];

				String s1 = sCurrentLine.split("\t")[1];
				
				if (s0.equals("IS")) {
					machine_code_file.write(s1 + "\t");
					if (sCurrentLine.split("\t").length == 5) {
						
						machine_code_file.write(sCurrentLine.split("\t")[2] + "\t");
						
						
						if (sCurrentLine.split("\t")[3].equals("L")) {
							int add = Integer.parseInt(sCurrentLine.split("\t")[4]);
							machine_code_file.write(littab[add-1][1]+"\n");
						
						}
						
						
						if (sCurrentLine.split("\t")[3].equals("S")) {
							int add1 = Integer.parseInt(sCurrentLine.split("\t")[4]);
							int i = 1;
							String l1;
							for (Map.Entry m : symtab.entrySet()) {
								if (i == add1) {
									machine_code_file.write((String) m.getValue());
								}
								i++;
							}
							
						}
					} else {
						machine_code_file.write("0\t000");
					}
				}

				
				
				//DRY RUN is a must
				
				if (s0.equals("AD")) {			
					littab_file.reset();
					if (s1.equals("02")) {			//if it is "END" stmt
						String s;
						while ((s = littab_file.readLine()) != null) {
							machine_code_file.write("00\t0\t00" + s.split("'")[1]);
							locptr++;
							//if(littab_file.readLine() != null)
							machine_code_file.write("\n"+locptr+"\t");
							
							
						}
					}
				}
				
				
				
				if(s0.equals("DL")&&s1.equals("02")){		//if it is DC stmt
					machine_code_file.write("00\t0\t00"+sCurrentLine.split("\t")[3]);
					
				}
				
				
				
				locptr++;
				machine_code_file.write("\n");
			}
			IC_file.close();
			symtab_file.close();
			littab_file.close();
			machine_code_file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}

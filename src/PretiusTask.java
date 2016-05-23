import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;

public class PretiusTask
{

	public static void main(String[] args)
	{
		String line;
		String[] parts;
		BigDecimal sum = new BigDecimal(0);
		File f;
		if (args.length < 1) //dla obslugi argumentow wiersza polecen
			f = new File("Plik z danymi.txt");
		else
			f = new File(args[0]);

		try
		{
			if (f.exists() && !f.isDirectory()) //dla pewnosci poprawnego otworzenia
			{
				BufferedReader br = new BufferedReader(
						new InputStreamReader(new FileInputStream(f), StandardCharsets.UTF_8));

				while ((line = br.readLine()) != null)
				{
					if (line.contains("@amount:")) //na wypadek pustych linii
					{
						parts = line.split("@amount:");
						String amount = parts[1].substring(0, parts[1].length() - 3);//kwota moze miec rozny format, ale na koncu zawsze ma "PLN"
						amount = amount.replace(",", ".");
						BigDecimal bd = new BigDecimal(amount);
						sum = sum.add(bd);
					}
				}
			}
			else
			{
				System.err.println("Plik o podanej nazwie nie zostal odnaleziony!");
			}

		}
		catch (Exception ex)
		{
			ex.printStackTrace();
		}
		System.out.println("Suma kwot zapisanych w pliku: " + sum + "PLN");
	}

}
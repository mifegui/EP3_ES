import java.io.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;


public class superLeitor {

  static String superX = "";
  static String superY = "";
  static Path path = Paths.get("./testeula.hex"); //seleciona o local que sera escrito

  public static void main(String[] args) {
    initFile();

    try {
      File file = new File("./testeula.ula"); //seleciona o local que sera lido
      Scanner scanner = new Scanner(file);
      String cmd = scanner.nextLine();
      if (cmd.length() == 7 && cmd.equals("inicio:")) { //programa:  'inicio:' ... 'fim.'
        do {
          cmd = scanner.nextLine();
          if (cmd.charAt(0) == 'X' || cmd.charAt(0) == 'Y') {
            ler(cmd);
          } else if (cmd.charAt(0) == 'W') {
            escrever(cmd);
          }
        } while (!(cmd.length() == 4 && cmd.equals("fim.")));
      }
    } catch (Exception e) {
      System.out.println("Erro - nao encontrado");
    }
  }

  public static void converter(String x, RandomAccessFile arq)
    throws Exception {
    System.out.println(superX + "" + superY + "" + x);
    String result = superX + "" + superY + "" + x;
    byte[] result_arq = result.getBytes();
    arq.write(result_arq);
    arq.writeBytes("\n");
  }

  /*
   *metodo que gera as saidas do programa
   *
   *nele nao ocorre nenhuma atribuicao de valor(X,Y,W), somente escrita em arquivo
   */
  public static void escrever(String comando) {
    String comandoEmSi = "";
    String result = "";
    byte[] result_arq;
    int length = comando.length();
    //comando: W=AnB
    for (int i = 2; i < length - 1; i++) {
      comandoEmSi = comandoEmSi + comando.charAt(i);
    }
    //comandoEmSi: AnB
    try {
      RandomAccessFile arq = new RandomAccessFile("testeula.hex", "rw"); // Cria o arquivo
      arq.seek(arq.length());
      switch (comandoEmSi) {
        case "An": //0
          converter("0", arq);
          break;
        case "Bn": //1
          converter("1", arq);
          break;
        case "umL": //2
          converter("2", arq);
          break;
        case "zeroL": //3
          converter("3", arq);
          break;
        case "nAeB": //4
          converter("4", arq);
          break;
        case "nAoB": //5
          converter("5", arq);
          break;
        case "AxB": //6
          converter("6", arq);
          break;
        case "AeBn": //7
          converter("7", arq);
          break;
        case "AnoB": //8
          converter("8", arq);
          break;
        case "nAxB": //9
          converter("9", arq);
          break;
        case "AoB": //A
          converter("A", arq);
          break;
        case "AeB": //B
          converter("B", arq);
          break;
        case "AneB": //C
          converter("C", arq);
          break;
        case "AoBn": //D
          converter("D", arq);
          break;
        case "copiaB": //E
          converter("E", arq);
          break;
        case "copiaA": //F
          converter("F", arq);
          break;
        default:
          System.out.println("Erro");
          break;
      }
      arq.close();
    } catch (Exception e) {
      System.out.println("Erro: nao aberto");
    }
  }

  /*
   *metodo para atribuir os valores recebidos
   *
   *tambem mantem a formatacao com todas as letras maiusculas
   *
   *OBS: nao gera nenhuma saida, somente efetua a troca de valores
   */
  public static void ler(String comando) {
    String valor = "";
    String a = "";
    int num = -1;
    int length = comando.length();
    // comando:X=12;
    for (int i = 2; i < length - 1; i++) {
      valor = valor + comando.charAt(i);
    }
    // valor:12;

    if (comando.charAt(0) == 'X') {
      // valor = "206"
      num = Integer.parseInt(valor);
      // num = 206
      superX = Integer.toHexString(num);
      if (superX.charAt(0) >= 'a' && superX.charAt(0) <= 'f') {
        superX = superX.toUpperCase();
      }
    } else {
      num = Integer.parseInt(valor);
      superY = Integer.toHexString(num);
      if (superY.charAt(0) >= 'a' && superY.charAt(0) <= 'f') {
        superY = superY.toUpperCase();
      }
    }
  }

  /*
   *testa no inicio do programa se o arquivo testeula.hex
   *ja existe. se existir, exclui lo
   */
  public boolean alreadyExists() {
    try {
      return path.toFile().exists();
    } catch (Exception e) {
      return false;
    }
  }

  public static void initFile() {
    if (path.toFile().exists()) {
      path.toFile().delete();
    }
  }
}

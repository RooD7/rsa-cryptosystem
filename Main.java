import java.util.*;
import java.security.SecureRandom;
import java.math.BigInteger;


public class Main {
	public static void main(String[] args) {

		// Gerador de numeros randomicos criptograficamente fortes
		SecureRandom r = new SecureRandom();
		String msg = "Paz e felicidade a todos!";
		String msgcifrada = null;
		String msgdecifrada = null;
		BigInteger n;
		int bitlen = 2048;

		// Geracao de 2 numeros primos grandes
		BigInteger p = new BigInteger(bitlen / 2, 100, r);
		BigInteger q = new BigInteger(bitlen / 2, 100, r);
		
		// Computa n = p * q
		n = p.multiply(q);

		//Computa a funcao totiente m = (p-1)(q-1)
		BigInteger m = (p.subtract(BigInteger.ONE))
						.multiply(q.subtract(BigInteger.ONE));

		// Escolhe um inteiro 'e', onde 
		// 		1 < 'e' < 'm', 
		//		'e' e 'm' sejam primos entre si
		BigInteger e = new BigInteger("3");
		
		// gcd = maior divisor comum
		while(m.gcd(e).intValue() > 1)
			e = e.add(new BigInteger("2"));

		// 'd' seja o inverso multiplicativo de 'e'
		BigInteger d = e.modInverse(m);

		System.out.println("\nNúmero primo grande p: "+p);
		System.out.println("\nNúmero primo grande q: "+q);
		System.out.println("\np * q: "+n);

		System.out.println("\nChave publica ");
		System.out.println("----- ------- ");

		System.out.println("\nFunção totiente m = (p-1)(q-1): "+n);

		System.out.println("\nInteiro 'e', onde 1 < 'e' < 'm': "+e);

		System.out.println("\nChave privada ");
		System.out.println("----- ------- ");
		System.out.println("\nInverso multiplicativo de 'e': "+d);

		//mensagem cifrada - RSA_encrypt()
        msgcifrada = new BigInteger(msg.getBytes()).modPow(e, n).toString();
        System.out.println("\nMensagem cifrada: "+ msgcifrada);

        //mensagem decifrada - RSA_decrypt()
        msgdecifrada = new String(new BigInteger(msgcifrada).modPow(d, n).toByteArray());
        System.out.println("\nMensagem decifrada: " +msgdecifrada);
	}
}
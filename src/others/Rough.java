package others;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.util.Arrays;

public class Rough {
	public static final PrintWriter outWriter = new PrintWriter(System.out);
	public static final int _BUFFER_SIZE = 1 << 16;
	public static final DataInputStream _din = new DataInputStream(System.in);
	public static final byte[] _buffer = new byte[_BUFFER_SIZE];
	public static int _bufferPointer = 0, _bytesRead = 0;

	public static void main() {
		int n = nextInt();
		long[] as = new long[n];
		for (int i = 0; i < as.length; i++) {
			as[i] = nextLong();
		}
		int out = 1, curr = 1;
		for (int i = 1; i < n; i++) {
			if (as[i - 1] * 2 >= as[i]) {
				out = Math.max(out, ++curr);
			} else {
				curr = 1;
			}
		}
		outWriter.println(out);
	}

	public static void main(String[] args) {
		main();
		outWriter.flush();
	}

	public static void _fillBuffer() {
		try {
			_bytesRead = _din.read(_buffer, _bufferPointer = 0, _buffer.length);
			if (_bytesRead == -1) {
				_buffer[0] = -1;
				throw new UncheckedIOException(new IOException("end of stream"));
			}
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
	}

	public static byte _read() {
		if (_bufferPointer == _bytesRead)
			_fillBuffer();
		return _buffer[_bufferPointer++];
	}

	public static String next() {
		byte[] strBuf = new byte[64];
		int cnt = 0, c;
		while ((c = _read()) != -1) {
			if (Character.isWhitespace(c)) {
				if (cnt == 0) {
					continue;
				} else {
					break;
				}
			}
			if (strBuf.length == cnt) {
				strBuf = Arrays.copyOf(strBuf, strBuf.length * 2);
			}
			strBuf[cnt++] = (byte) c;
		}
		return new String(strBuf, 0, cnt);
	}

	public static String nextLine() {
		byte[] strBuf = new byte[64];
		int cnt = 0, c;
		while ((c = _read()) != -1) {
			if (c == '\n') {
				if (cnt == 0) {
					continue;
				} else {
					break;
				}
			}
			if (strBuf.length == cnt) {
				strBuf = Arrays.copyOf(strBuf, strBuf.length * 2);
			}
			strBuf[cnt++] = (byte) c;
		}
		return new String(strBuf, 0, cnt);
	}

	public static int nextInt() {
		int ret = 0;
		byte c = _read();
		while (c <= ' ')
			c = _read();
		boolean neg = (c == '-');
		if (neg)
			c = _read();
		do {
			ret = ret * 10 + c - '0';
		} while ((c = _read()) >= '0' && c <= '9');
		if (neg)
			return -ret;
		return ret;
	}

	public static long nextLong() {
		long ret = 0;
		byte c = _read();
		while (c <= ' ')
			c = _read();
		boolean neg = (c == '-');
		if (neg)
			c = _read();
		do {
			ret = ret * 10 + c - '0';
		} while ((c = _read()) >= '0' && c <= '9');
		if (neg)
			return -ret;
		return ret;
	}

	public static double nextDouble() {
		double ret = 0, div = 1;
		byte c = _read();
		while (c <= ' ')
			c = _read();
		boolean neg = (c == '-');
		if (neg)
			c = _read();
		do {
			ret = ret * 10 + c - '0';
		} while ((c = _read()) >= '0' && c <= '9');
		if (c == '.') {
			while ((c = _read()) >= '0' && c <= '9') {
				ret += (c - '0') / (div *= 10);
			}
		}
		if (neg)
			return -ret;
		return ret;
	}
}
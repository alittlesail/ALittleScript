/* The following code was generated by JFlex 1.7.0 tweaked for IntelliJ platform */

package plugin;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;

import static com.intellij.psi.TokenType.BAD_CHARACTER;
import static com.intellij.psi.TokenType.WHITE_SPACE;
import static plugin.psi.ALittleTypes.*;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.7.0
 * from the specification file <tt>_ALittleLexer.flex</tt>
 */
public class _ALittleLexer implements FlexLexer {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0, 0
  };

  /** 
   * Translates characters to character classes
   * Chosen bits are [9, 6, 6]
   * Total runtime size is 1568 bytes
   */
  public static int ZZ_CMAP(int ch) {
    return ZZ_CMAP_A[(ZZ_CMAP_Y[ZZ_CMAP_Z[ch>>12]|((ch>>6)&0x3f)]<<6)|(ch&0x3f)];
  }

  /* The ZZ_CMAP_Z table has 272 entries */
  static final char ZZ_CMAP_Z[] = zzUnpackCMap(
    "\1\0\1\100\1\200\u010d\100");

  /* The ZZ_CMAP_Y table has 192 entries */
  static final char ZZ_CMAP_Y[] = zzUnpackCMap(
    "\1\0\1\1\1\2\175\3\1\4\77\3");

  /* The ZZ_CMAP_A table has 320 entries */
  static final char ZZ_CMAP_A[] = zzUnpackCMap(
    "\11\0\1\3\4\2\22\0\1\3\1\27\1\12\2\0\1\36\1\33\1\40\1\21\1\22\1\35\1\30\1"+
    "\25\1\31\1\11\1\4\1\5\3\10\1\75\1\10\1\74\3\10\1\23\1\24\1\34\1\26\1\37\2"+
    "\0\2\7\1\64\2\7\1\72\2\14\1\73\2\14\1\71\1\70\15\14\1\17\1\13\1\20\1\0\1\14"+
    "\1\0\1\55\1\51\1\53\1\57\1\42\1\65\1\43\1\66\1\44\1\14\1\67\1\52\1\63\1\61"+
    "\1\56\1\47\1\14\1\41\1\45\1\46\1\50\1\54\1\62\1\6\1\60\1\14\1\15\1\32\1\16"+
    "\7\0\1\1\242\0\2\1\26\0");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\4\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\2\1"+
    "\1\26\1\27\1\30\1\31\1\32\24\5\1\33\1\34"+
    "\1\0\1\4\1\35\1\0\1\36\1\0\1\37\1\40"+
    "\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50"+
    "\1\51\1\52\4\5\1\53\1\54\17\5\1\55\15\5"+
    "\1\4\5\5\1\56\1\57\1\60\16\5\1\61\3\5"+
    "\1\62\4\5\1\63\1\5\1\64\1\65\2\5\1\66"+
    "\3\5\1\67\1\70\3\5\1\71\1\72\4\5\1\73"+
    "\1\74\1\5\1\75\1\5\1\76\3\5\1\77\2\5"+
    "\1\100\1\5\1\101\13\5\1\102\1\103\1\104\1\105"+
    "\1\5\1\106\1\5\1\107\1\110\2\5\1\111\1\5"+
    "\1\112\1\113\1\114\1\115\2\5\1\116\1\117\3\5"+
    "\1\120\1\121\2\5\1\122\1\123\2\5\1\124\1\125";

  private static int [] zzUnpackAction() {
    int [] result = new int[218];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\76\0\174\0\272\0\370\0\u0136\0\u0174\0\u01b2"+
    "\0\u01f0\0\76\0\76\0\76\0\76\0\76\0\76\0\76"+
    "\0\76\0\76\0\76\0\u022e\0\u026c\0\u02aa\0\u02e8\0\u0326"+
    "\0\u0364\0\u03a2\0\u03e0\0\u041e\0\u045c\0\76\0\u049a\0\u04d8"+
    "\0\u0516\0\u0554\0\u0592\0\u05d0\0\u060e\0\u064c\0\u068a\0\u06c8"+
    "\0\u0706\0\u0744\0\u0782\0\u07c0\0\u07fe\0\u083c\0\u087a\0\u08b8"+
    "\0\u08f6\0\u0934\0\u0972\0\76\0\u09b0\0\u09ee\0\76\0\u01f0"+
    "\0\76\0\u0a2c\0\76\0\76\0\76\0\76\0\76\0\76"+
    "\0\76\0\76\0\76\0\76\0\76\0\76\0\u0a6a\0\u0aa8"+
    "\0\u0ae6\0\u0b24\0\u0b62\0\u0136\0\u0ba0\0\u0bde\0\u0c1c\0\u0c5a"+
    "\0\u0c98\0\u0cd6\0\u0d14\0\u0d52\0\u0d90\0\u0dce\0\u0e0c\0\u0e4a"+
    "\0\u0e88\0\u0ec6\0\u0f04\0\u0f42\0\u0f80\0\u0fbe\0\u0ffc\0\u103a"+
    "\0\u1078\0\u10b6\0\u10f4\0\u1132\0\u1170\0\u11ae\0\u11ec\0\u122a"+
    "\0\u1268\0\u09b0\0\u12a6\0\u12e4\0\u1322\0\u1360\0\u139e\0\u0136"+
    "\0\u0136\0\u0136\0\u13dc\0\u141a\0\u1458\0\u1496\0\u14d4\0\u1512"+
    "\0\u1550\0\u158e\0\u15cc\0\u160a\0\u1648\0\u1686\0\u16c4\0\u1702"+
    "\0\u0136\0\u1740\0\u177e\0\u17bc\0\u0136\0\u17fa\0\u1838\0\u1876"+
    "\0\u18b4\0\u0136\0\u18f2\0\u0136\0\u0136\0\u1930\0\u196e\0\u0136"+
    "\0\u19ac\0\u19ea\0\u1a28\0\u1a66\0\u0136\0\u1aa4\0\u1ae2\0\u1b20"+
    "\0\u0136\0\u0136\0\u1b5e\0\u1b9c\0\u1bda\0\u1c18\0\u0136\0\u0136"+
    "\0\u1c56\0\u0136\0\u1c94\0\u0136\0\u1cd2\0\u1d10\0\u1d4e\0\u0136"+
    "\0\u1d8c\0\u1dca\0\u0136\0\u1e08\0\u0136\0\u1e46\0\u1e84\0\u1ec2"+
    "\0\u1f00\0\u1f3e\0\u1f7c\0\u1fba\0\u1ff8\0\u2036\0\u2074\0\u20b2"+
    "\0\u0136\0\u0136\0\u0136\0\u0136\0\u20f0\0\u0136\0\u212e\0\u0136"+
    "\0\u0136\0\u216c\0\u21aa\0\u0136\0\u21e8\0\u0136\0\u0136\0\u0136"+
    "\0\u0136\0\u2226\0\u2264\0\u0136\0\u0136\0\u22a2\0\u22e0\0\u231e"+
    "\0\u0136\0\u0136\0\u235c\0\u239a\0\u0136\0\u0136\0\u23d8\0\u2416"+
    "\0\u0136\0\u0136";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[218];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\2\2\2\3\1\4\1\5\2\6\1\7\1\10\1\11"+
    "\1\12\1\6\1\13\1\14\1\15\1\16\1\17\1\20"+
    "\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30"+
    "\1\31\1\32\1\33\1\34\1\35\1\36\1\37\1\40"+
    "\1\41\1\42\1\43\1\44\1\45\1\6\1\46\1\6"+
    "\1\47\1\6\1\50\1\6\1\51\1\52\1\53\1\54"+
    "\1\6\1\55\1\56\2\6\1\57\1\60\1\61\1\62"+
    "\2\7\100\0\2\3\76\0\1\63\21\0\1\64\54\0"+
    "\1\7\1\65\1\0\1\7\1\66\62\0\2\7\5\0"+
    "\4\6\3\0\1\6\24\0\35\6\5\0\1\7\2\0"+
    "\1\7\1\66\62\0\2\7\11\0\1\67\64\0\12\70"+
    "\1\71\1\72\62\70\26\0\1\73\75\0\1\74\75\0"+
    "\1\75\1\0\1\76\73\0\1\77\2\0\1\100\76\0"+
    "\1\101\76\0\1\102\70\0\1\103\75\0\1\104\75\0"+
    "\1\105\75\0\1\106\54\0\4\6\3\0\1\6\24\0"+
    "\1\6\1\107\33\6\5\0\4\6\3\0\1\6\24\0"+
    "\11\6\1\110\6\6\1\111\14\6\5\0\4\6\3\0"+
    "\1\6\24\0\1\6\1\112\33\6\5\0\4\6\3\0"+
    "\1\6\24\0\20\6\1\113\3\6\1\114\10\6\5\0"+
    "\4\6\3\0\1\6\24\0\1\6\1\115\3\6\1\116"+
    "\27\6\5\0\4\6\3\0\1\6\24\0\1\117\24\6"+
    "\1\120\7\6\5\0\4\6\3\0\1\6\24\0\1\121"+
    "\6\6\1\122\25\6\5\0\4\6\3\0\1\6\24\0"+
    "\1\123\2\6\1\124\11\6\1\125\17\6\5\0\4\6"+
    "\3\0\1\6\24\0\11\6\1\126\2\6\1\127\20\6"+
    "\5\0\4\6\3\0\1\6\24\0\4\6\1\130\2\6"+
    "\1\131\10\6\1\132\1\133\13\6\5\0\4\6\3\0"+
    "\1\6\24\0\15\6\1\134\17\6\5\0\4\6\3\0"+
    "\1\6\24\0\3\6\1\135\31\6\5\0\4\6\3\0"+
    "\1\6\24\0\1\6\1\136\5\6\1\137\4\6\1\140"+
    "\20\6\5\0\4\6\3\0\1\6\24\0\25\6\1\141"+
    "\7\6\5\0\4\6\3\0\1\6\24\0\5\6\1\142"+
    "\27\6\5\0\4\6\3\0\1\6\24\0\7\6\1\143"+
    "\4\6\1\144\1\145\17\6\5\0\4\6\3\0\1\6"+
    "\24\0\14\6\1\146\20\6\5\0\4\6\3\0\1\6"+
    "\24\0\3\6\1\147\31\6\5\0\4\6\3\0\1\6"+
    "\24\0\7\6\1\150\25\6\5\0\4\6\3\0\1\6"+
    "\24\0\33\6\1\151\1\6\1\63\2\0\73\63\5\0"+
    "\1\152\1\0\2\152\31\0\1\152\6\0\1\152\1\0"+
    "\1\152\1\0\1\152\1\0\1\152\4\0\2\152\4\0"+
    "\1\152\1\0\2\152\5\0\1\66\2\0\1\66\63\0"+
    "\2\66\1\70\2\0\73\70\5\0\4\6\3\0\1\6"+
    "\24\0\2\6\1\153\2\6\1\154\16\6\1\155\10\6"+
    "\5\0\4\6\3\0\1\6\24\0\4\6\1\156\30\6"+
    "\5\0\4\6\3\0\1\6\24\0\7\6\1\157\25\6"+
    "\5\0\4\6\3\0\1\6\24\0\5\6\1\160\27\6"+
    "\5\0\4\6\3\0\1\6\24\0\5\6\1\161\27\6"+
    "\5\0\4\6\3\0\1\6\24\0\5\6\1\162\27\6"+
    "\5\0\4\6\3\0\1\6\24\0\1\163\13\6\1\164"+
    "\20\6\5\0\4\6\3\0\1\6\24\0\7\6\1\165"+
    "\25\6\5\0\4\6\3\0\1\6\24\0\3\6\1\166"+
    "\31\6\5\0\4\6\3\0\1\6\24\0\3\6\1\167"+
    "\11\6\1\170\17\6\5\0\4\6\3\0\1\6\24\0"+
    "\10\6\1\171\24\6\5\0\4\6\3\0\1\6\24\0"+
    "\1\6\1\172\33\6\5\0\4\6\3\0\1\6\24\0"+
    "\20\6\1\173\14\6\5\0\4\6\3\0\1\6\24\0"+
    "\15\6\1\174\17\6\5\0\4\6\3\0\1\6\24\0"+
    "\14\6\1\175\20\6\5\0\4\6\3\0\1\6\24\0"+
    "\4\6\1\176\30\6\5\0\4\6\3\0\1\6\24\0"+
    "\17\6\1\177\15\6\5\0\4\6\3\0\1\6\24\0"+
    "\5\6\1\200\27\6\5\0\4\6\3\0\1\6\24\0"+
    "\17\6\1\201\15\6\5\0\4\6\3\0\1\6\24\0"+
    "\14\6\1\202\20\6\5\0\4\6\3\0\1\6\24\0"+
    "\7\6\1\203\25\6\5\0\4\6\3\0\1\6\24\0"+
    "\1\6\1\204\33\6\5\0\4\6\3\0\1\6\24\0"+
    "\21\6\1\205\13\6\5\0\4\6\3\0\1\6\24\0"+
    "\11\6\1\206\23\6\5\0\4\6\3\0\1\6\24\0"+
    "\22\6\1\207\12\6\5\0\4\6\3\0\1\6\24\0"+
    "\3\6\1\210\31\6\5\0\4\6\3\0\1\6\24\0"+
    "\15\6\1\211\17\6\5\0\4\6\3\0\1\6\24\0"+
    "\20\6\1\212\14\6\5\0\4\6\3\0\1\6\24\0"+
    "\11\6\1\213\23\6\5\0\4\6\3\0\1\6\24\0"+
    "\1\214\34\6\5\0\4\6\3\0\1\6\24\0\6\6"+
    "\1\215\26\6\5\0\4\6\3\0\1\6\24\0\4\6"+
    "\1\216\30\6\5\0\4\6\3\0\1\6\24\0\20\6"+
    "\1\217\14\6\5\0\4\6\3\0\1\6\24\0\34\6"+
    "\1\220\5\0\4\6\3\0\1\6\24\0\3\6\1\221"+
    "\31\6\5\0\4\6\3\0\1\6\24\0\7\6\1\222"+
    "\25\6\5\0\4\6\3\0\1\6\24\0\11\6\1\223"+
    "\23\6\5\0\4\6\3\0\1\6\24\0\1\6\1\224"+
    "\33\6\5\0\4\6\3\0\1\6\24\0\22\6\1\225"+
    "\12\6\5\0\4\6\3\0\1\6\24\0\3\6\1\226"+
    "\3\6\1\227\25\6\5\0\4\6\3\0\1\6\24\0"+
    "\5\6\1\230\27\6\5\0\4\6\3\0\1\6\24\0"+
    "\1\6\1\231\33\6\5\0\4\6\3\0\1\6\24\0"+
    "\4\6\1\232\30\6\5\0\4\6\3\0\1\6\24\0"+
    "\13\6\1\233\21\6\5\0\4\6\3\0\1\6\24\0"+
    "\5\6\1\234\27\6\5\0\4\6\3\0\1\6\24\0"+
    "\11\6\1\235\23\6\5\0\4\6\3\0\1\6\24\0"+
    "\14\6\1\236\20\6\5\0\4\6\3\0\1\6\24\0"+
    "\16\6\1\237\16\6\5\0\4\6\3\0\1\6\24\0"+
    "\11\6\1\240\23\6\5\0\4\6\3\0\1\6\24\0"+
    "\4\6\1\241\30\6\5\0\4\6\3\0\1\6\24\0"+
    "\5\6\1\242\27\6\5\0\4\6\3\0\1\6\24\0"+
    "\20\6\1\243\14\6\5\0\4\6\3\0\1\6\24\0"+
    "\15\6\1\244\17\6\5\0\4\6\3\0\1\6\24\0"+
    "\3\6\1\245\31\6\5\0\4\6\3\0\1\6\24\0"+
    "\10\6\1\246\24\6\5\0\4\6\3\0\1\6\24\0"+
    "\11\6\1\247\23\6\5\0\4\6\3\0\1\6\24\0"+
    "\11\6\1\250\23\6\5\0\4\6\3\0\1\6\24\0"+
    "\1\6\1\251\33\6\5\0\4\6\3\0\1\6\24\0"+
    "\11\6\1\252\23\6\5\0\4\6\3\0\1\6\24\0"+
    "\1\253\34\6\5\0\4\6\3\0\1\6\24\0\4\6"+
    "\1\254\30\6\5\0\4\6\3\0\1\6\24\0\5\6"+
    "\1\255\27\6\5\0\4\6\3\0\1\6\24\0\12\6"+
    "\1\256\22\6\5\0\4\6\3\0\1\6\24\0\4\6"+
    "\1\257\30\6\5\0\4\6\3\0\1\6\24\0\1\260"+
    "\34\6\5\0\4\6\3\0\1\6\24\0\1\6\1\261"+
    "\33\6\5\0\4\6\3\0\1\6\24\0\3\6\1\262"+
    "\31\6\5\0\4\6\3\0\1\6\24\0\20\6\1\263"+
    "\14\6\5\0\4\6\3\0\1\6\24\0\12\6\1\264"+
    "\22\6\5\0\4\6\3\0\1\6\24\0\3\6\1\265"+
    "\31\6\5\0\4\6\3\0\1\6\24\0\14\6\1\266"+
    "\20\6\5\0\4\6\3\0\1\6\24\0\1\6\1\267"+
    "\33\6\5\0\4\6\3\0\1\6\24\0\3\6\1\270"+
    "\31\6\5\0\4\6\3\0\1\6\24\0\26\6\1\271"+
    "\6\6\5\0\4\6\3\0\1\6\24\0\4\6\1\272"+
    "\30\6\5\0\4\6\3\0\1\6\24\0\12\6\1\273"+
    "\22\6\5\0\4\6\3\0\1\6\24\0\5\6\1\274"+
    "\27\6\5\0\4\6\3\0\1\6\24\0\11\6\1\275"+
    "\23\6\5\0\4\6\3\0\1\6\24\0\16\6\1\276"+
    "\16\6\5\0\4\6\3\0\1\6\24\0\4\6\1\277"+
    "\30\6\5\0\4\6\3\0\1\6\24\0\1\6\1\300"+
    "\33\6\5\0\4\6\3\0\1\6\24\0\1\6\1\301"+
    "\33\6\5\0\4\6\3\0\1\6\24\0\5\6\1\302"+
    "\27\6\5\0\4\6\3\0\1\6\24\0\5\6\1\303"+
    "\27\6\5\0\4\6\3\0\1\6\24\0\20\6\1\304"+
    "\14\6\5\0\4\6\3\0\1\6\24\0\12\6\1\305"+
    "\22\6\5\0\4\6\3\0\1\6\24\0\24\6\1\306"+
    "\10\6\5\0\4\6\3\0\1\6\24\0\2\6\1\307"+
    "\32\6\5\0\4\6\3\0\1\6\24\0\5\6\1\310"+
    "\27\6\5\0\4\6\3\0\1\6\24\0\12\6\1\311"+
    "\22\6\5\0\4\6\3\0\1\6\24\0\5\6\1\312"+
    "\27\6\5\0\4\6\3\0\1\6\24\0\12\6\1\313"+
    "\22\6\5\0\4\6\3\0\1\6\24\0\12\6\1\314"+
    "\22\6\5\0\4\6\3\0\1\6\24\0\1\6\1\315"+
    "\33\6\5\0\4\6\3\0\1\6\24\0\6\6\1\316"+
    "\26\6\5\0\4\6\3\0\1\6\24\0\15\6\1\317"+
    "\17\6\5\0\4\6\3\0\1\6\24\0\1\6\1\320"+
    "\33\6\5\0\4\6\3\0\1\6\24\0\5\6\1\321"+
    "\27\6\5\0\4\6\3\0\1\6\24\0\1\6\1\322"+
    "\33\6\5\0\4\6\3\0\1\6\24\0\5\6\1\323"+
    "\27\6\5\0\4\6\3\0\1\6\24\0\14\6\1\324"+
    "\20\6\5\0\4\6\3\0\1\6\24\0\1\325\34\6"+
    "\5\0\4\6\3\0\1\6\24\0\1\326\34\6\5\0"+
    "\4\6\3\0\1\6\24\0\1\6\1\327\33\6\5\0"+
    "\4\6\3\0\1\6\24\0\12\6\1\330\22\6\5\0"+
    "\4\6\3\0\1\6\24\0\16\6\1\331\16\6\5\0"+
    "\4\6\3\0\1\6\24\0\1\6\1\332\33\6";

  private static int [] zzUnpackTrans() {
    int [] result = new int[9300];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String[] ZZ_ERROR_MSG = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\1\0\1\11\7\1\12\11\12\1\1\11\25\1\1\11"+
    "\1\0\1\1\1\11\1\0\1\11\1\0\14\11\224\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[218];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private CharSequence zzBuffer = "";

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /**
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;

  /* user code: */
  public _ALittleLexer() {
    this((java.io.Reader)null);
  }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public _ALittleLexer(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    int size = 0;
    for (int i = 0, length = packed.length(); i < length; i += 2) {
      size += packed.charAt(i);
    }
    char[] map = new char[size];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < packed.length()) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }

  public final int getTokenStart() {
    return zzStartRead;
  }

  public final int getTokenEnd() {
    return getTokenStart() + yylength();
  }

  public void reset(CharSequence buffer, int start, int end, int initialState) {
    zzBuffer = buffer;
    zzCurrentPos = zzMarkedPos = zzStartRead = start;
    zzAtEOF  = false;
    zzAtBOL = true;
    zzEndRead = end;
    yybegin(initialState);
  }

  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   *
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {
    return true;
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final CharSequence yytext() {
    return zzBuffer.subSequence(zzStartRead, zzMarkedPos);
  }


  /**
   * Returns the character at position <tt>pos</tt> from the
   * matched text.
   *
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch.
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer.charAt(zzStartRead+pos);
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of
   * yypushback(int) and a match-all fallback rule) this method
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  }


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public IElementType advance() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    CharSequence zzBufferL = zzBuffer;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;

      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {

          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL/*, zzEndReadL*/);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + ZZ_CMAP(zzInput) ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
        return null;
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { return BAD_CHARACTER;
            } 
            // fall through
          case 86: break;
          case 2: 
            { return WHITE_SPACE;
            } 
            // fall through
          case 87: break;
          case 3: 
            { return QUOTIENT;
            } 
            // fall through
          case 88: break;
          case 4: 
            { return DIGIT_CONTENT;
            } 
            // fall through
          case 89: break;
          case 5: 
            { return ID_CONTENT;
            } 
            // fall through
          case 90: break;
          case 6: 
            { return DOT;
            } 
            // fall through
          case 91: break;
          case 7: 
            { return QUOTE;
            } 
            // fall through
          case 92: break;
          case 8: 
            { return BACK;
            } 
            // fall through
          case 93: break;
          case 9: 
            { return LBRACE;
            } 
            // fall through
          case 94: break;
          case 10: 
            { return RBRACE;
            } 
            // fall through
          case 95: break;
          case 11: 
            { return LBRACK;
            } 
            // fall through
          case 96: break;
          case 12: 
            { return RBRACK;
            } 
            // fall through
          case 97: break;
          case 13: 
            { return LPAREN;
            } 
            // fall through
          case 98: break;
          case 14: 
            { return RPAREN;
            } 
            // fall through
          case 99: break;
          case 15: 
            { return COLON;
            } 
            // fall through
          case 100: break;
          case 16: 
            { return SEMI;
            } 
            // fall through
          case 101: break;
          case 17: 
            { return COMMA;
            } 
            // fall through
          case 102: break;
          case 18: 
            { return ASSIGN;
            } 
            // fall through
          case 103: break;
          case 19: 
            { return NOT;
            } 
            // fall through
          case 104: break;
          case 20: 
            { return PLUS;
            } 
            // fall through
          case 105: break;
          case 21: 
            { return MINUS;
            } 
            // fall through
          case 106: break;
          case 22: 
            { return LESS;
            } 
            // fall through
          case 107: break;
          case 23: 
            { return MUL;
            } 
            // fall through
          case 108: break;
          case 24: 
            { return REMAINDER;
            } 
            // fall through
          case 109: break;
          case 25: 
            { return GREATER;
            } 
            // fall through
          case 110: break;
          case 26: 
            { return APOS;
            } 
            // fall through
          case 111: break;
          case 27: 
            { return COMMENT;
            } 
            // fall through
          case 112: break;
          case 28: 
            { return QUOTIENT_ASSIGN;
            } 
            // fall through
          case 113: break;
          case 29: 
            { return CONCAT;
            } 
            // fall through
          case 114: break;
          case 30: 
            { return STRING_CONTENT;
            } 
            // fall through
          case 115: break;
          case 31: 
            { return EQ;
            } 
            // fall through
          case 116: break;
          case 32: 
            { return NOT_EQ;
            } 
            // fall through
          case 117: break;
          case 33: 
            { return PLUS_ASSIGN;
            } 
            // fall through
          case 118: break;
          case 34: 
            { return PLUS_PLUS;
            } 
            // fall through
          case 119: break;
          case 35: 
            { return MINUS_ASSIGN;
            } 
            // fall through
          case 120: break;
          case 36: 
            { return MINUS_MINUS;
            } 
            // fall through
          case 121: break;
          case 37: 
            { return COND_OR;
            } 
            // fall through
          case 122: break;
          case 38: 
            { return COND_AND;
            } 
            // fall through
          case 123: break;
          case 39: 
            { return LESS_OR_EQUAL;
            } 
            // fall through
          case 124: break;
          case 40: 
            { return MUL_ASSIGN;
            } 
            // fall through
          case 125: break;
          case 41: 
            { return REMAINDER_ASSIGN;
            } 
            // fall through
          case 126: break;
          case 42: 
            { return GREATER_OR_EQUAL;
            } 
            // fall through
          case 127: break;
          case 43: 
            { return IN;
            } 
            // fall through
          case 128: break;
          case 44: 
            { return IF;
            } 
            // fall through
          case 129: break;
          case 45: 
            { return DO;
            } 
            // fall through
          case 130: break;
          case 46: 
            { return GET;
            } 
            // fall through
          case 131: break;
          case 47: 
            { return INT;
            } 
            // fall through
          case 132: break;
          case 48: 
            { return SET;
            } 
            // fall through
          case 133: break;
          case 49: 
            { return ANY;
            } 
            // fall through
          case 134: break;
          case 50: 
            { return NEW;
            } 
            // fall through
          case 135: break;
          case 51: 
            { return FUN;
            } 
            // fall through
          case 136: break;
          case 52: 
            { return FOR;
            } 
            // fall through
          case 137: break;
          case 53: 
            { return MAP;
            } 
            // fall through
          case 138: break;
          case 54: 
            { return I64;
            } 
            // fall through
          case 139: break;
          case 55: 
            { return ELSE;
            } 
            // fall through
          case 140: break;
          case 56: 
            { return ENUM;
            } 
            // fall through
          case 141: break;
          case 57: 
            { return TRUE;
            } 
            // fall through
          case 142: break;
          case 58: 
            { return THIS;
            } 
            // fall through
          case 143: break;
          case 59: 
            { return BIND;
            } 
            // fall through
          case 144: break;
          case 60: 
            { return BOOL;
            } 
            // fall through
          case 145: break;
          case 61: 
            { return CAST;
            } 
            // fall through
          case 146: break;
          case 62: 
            { return AUTO;
            } 
            // fall through
          case 147: break;
          case 63: 
            { return NULL;
            } 
            // fall through
          case 148: break;
          case 64: 
            { return CTOR;
            } 
            // fall through
          case 149: break;
          case 65: 
            { return LIST;
            } 
            // fall through
          case 150: break;
          case 66: 
            { return BREAK;
            } 
            // fall through
          case 151: break;
          case 67: 
            { return CLASS;
            } 
            // fall through
          case 152: break;
          case 68: 
            { return ASYNC;
            } 
            // fall through
          case 153: break;
          case 69: 
            { return AWAIT;
            } 
            // fall through
          case 154: break;
          case 70: 
            { return YIELD;
            } 
            // fall through
          case 155: break;
          case 71: 
            { return WHILE;
            } 
            // fall through
          case 156: break;
          case 72: 
            { return FALSE;
            } 
            // fall through
          case 157: break;
          case 73: 
            { return RETURN;
            } 
            // fall through
          case 158: break;
          case 74: 
            { return ELSEIF;
            } 
            // fall through
          case 159: break;
          case 75: 
            { return STRING;
            } 
            // fall through
          case 160: break;
          case 76: 
            { return STRUCT;
            } 
            // fall through
          case 161: break;
          case 77: 
            { return STATIC;
            } 
            // fall through
          case 162: break;
          case 78: 
            { return PUBLIC;
            } 
            // fall through
          case 163: break;
          case 79: 
            { return DOUBLE;
            } 
            // fall through
          case 164: break;
          case 80: 
            { return REFLECT;
            } 
            // fall through
          case 165: break;
          case 81: 
            { return PRIVATE;
            } 
            // fall through
          case 166: break;
          case 82: 
            { return FUNCTOR;
            } 
            // fall through
          case 167: break;
          case 83: 
            { return REGISTER;
            } 
            // fall through
          case 168: break;
          case 84: 
            { return PROTECTED;
            } 
            // fall through
          case 169: break;
          case 85: 
            { return NAMESPACE;
            } 
            // fall through
          case 170: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}

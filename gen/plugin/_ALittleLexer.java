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
    "\25\1\31\1\11\1\4\1\5\3\10\1\102\1\10\1\101\3\10\1\23\1\24\1\34\1\26\1\37"+
    "\1\0\1\41\2\7\1\60\1\45\1\7\1\77\1\14\1\42\1\100\2\14\1\76\1\55\7\14\1\54"+
    "\5\14\1\17\1\13\1\20\1\0\1\14\1\0\1\52\1\70\1\71\1\53\1\65\1\73\1\57\1\74"+
    "\1\66\1\14\1\75\1\51\1\72\1\50\1\46\1\44\1\63\1\64\1\56\1\43\1\67\1\61\1\47"+
    "\1\6\1\62\1\14\1\15\1\32\1\16\7\0\1\1\242\0\2\1\26\0");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\4\1\6"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\2\1"+
    "\1\26\1\27\1\30\1\31\1\32\1\1\25\5\1\33"+
    "\1\34\1\0\1\4\1\35\1\0\1\36\1\0\1\37"+
    "\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47"+
    "\1\50\1\51\1\52\3\0\15\5\1\53\11\5\1\54"+
    "\1\55\14\5\1\4\1\56\4\0\11\5\1\57\2\5"+
    "\1\60\4\5\1\61\2\5\1\62\1\63\7\5\1\64"+
    "\6\5\1\65\1\5\1\66\2\5\1\67\1\0\1\70"+
    "\1\0\1\71\1\72\2\5\1\73\5\5\1\74\3\5"+
    "\1\75\4\5\1\76\4\5\1\77\1\100\1\5\1\101"+
    "\1\5\1\102\1\5\1\103\1\5\1\104\1\5\1\105"+
    "\1\0\1\106\1\107\3\5\1\110\1\5\1\111\1\5"+
    "\1\112\4\5\1\113\4\5\1\114\1\115\1\116\1\117"+
    "\1\5\2\0\1\120\2\5\1\121\1\5\1\122\1\123"+
    "\1\124\1\125\1\126\1\127\2\5\1\130\1\5\2\0"+
    "\1\5\1\131\2\5\1\132\1\133\2\0\2\5\1\134"+
    "\2\0\1\135\1\136\3\0\1\137\1\0\1\140";

  private static int [] zzUnpackAction() {
    int [] result = new int[264];
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
    "\0\0\0\103\0\206\0\311\0\u010c\0\u014f\0\u0192\0\u01d5"+
    "\0\u0218\0\103\0\103\0\103\0\103\0\103\0\103\0\103"+
    "\0\103\0\103\0\103\0\u025b\0\u029e\0\u02e1\0\u0324\0\u0367"+
    "\0\u03aa\0\u03ed\0\u0430\0\u0473\0\u04b6\0\103\0\u04f9\0\u053c"+
    "\0\u057f\0\u05c2\0\u0605\0\u0648\0\u068b\0\u06ce\0\u0711\0\u0754"+
    "\0\u0797\0\u07da\0\u081d\0\u0860\0\u08a3\0\u08e6\0\u0929\0\u096c"+
    "\0\u09af\0\u09f2\0\u0a35\0\u0a78\0\u0abb\0\103\0\u0afe\0\u0b41"+
    "\0\u0b84\0\u0218\0\103\0\u0bc7\0\103\0\103\0\103\0\103"+
    "\0\103\0\103\0\103\0\103\0\103\0\103\0\103\0\103"+
    "\0\u0c0a\0\u0c4d\0\u0c90\0\u0cd3\0\u0d16\0\u0d59\0\u0d9c\0\u0ddf"+
    "\0\u0e22\0\u0e65\0\u0ea8\0\u0eeb\0\u0f2e\0\u0f71\0\u0fb4\0\u0ff7"+
    "\0\u103a\0\u107d\0\u10c0\0\u1103\0\u1146\0\u1189\0\u11cc\0\u120f"+
    "\0\u1252\0\u1295\0\u12d8\0\u014f\0\u131b\0\u135e\0\u13a1\0\u13e4"+
    "\0\u1427\0\u146a\0\u14ad\0\u14f0\0\u1533\0\u1576\0\u15b9\0\u15fc"+
    "\0\u0afe\0\103\0\u163f\0\u1682\0\u16c5\0\u1708\0\u174b\0\u178e"+
    "\0\u17d1\0\u1814\0\u1857\0\u189a\0\u18dd\0\u1920\0\u1963\0\u014f"+
    "\0\u19a6\0\u19e9\0\u014f\0\u1a2c\0\u1a6f\0\u1ab2\0\u1af5\0\u014f"+
    "\0\u1b38\0\u1b7b\0\u014f\0\u014f\0\u1bbe\0\u1c01\0\u1c44\0\u1c87"+
    "\0\u1cca\0\u1d0d\0\u1d50\0\u014f\0\u1d93\0\u1dd6\0\u1e19\0\u1e5c"+
    "\0\u1e9f\0\u1ee2\0\u014f\0\u1f25\0\u014f\0\u1f68\0\u1fab\0\u014f"+
    "\0\u1fee\0\103\0\u2031\0\103\0\u014f\0\u2074\0\u20b7\0\u014f"+
    "\0\u20fa\0\u213d\0\u2180\0\u21c3\0\u2206\0\u014f\0\u2249\0\u228c"+
    "\0\u22cf\0\u014f\0\u2312\0\u2355\0\u2398\0\u23db\0\u014f\0\u241e"+
    "\0\u2461\0\u24a4\0\u24e7\0\u014f\0\u252a\0\u256d\0\u014f\0\u25b0"+
    "\0\u014f\0\u25f3\0\u014f\0\u2636\0\u014f\0\u2679\0\u26bc\0\u26ff"+
    "\0\u014f\0\u014f\0\u2742\0\u2785\0\u27c8\0\u014f\0\u280b\0\u014f"+
    "\0\u284e\0\u014f\0\u2891\0\u28d4\0\u2917\0\u295a\0\u014f\0\u299d"+
    "\0\u29e0\0\u2a23\0\u2a66\0\u014f\0\u014f\0\u014f\0\u014f\0\u2aa9"+
    "\0\u2aec\0\u2b2f\0\103\0\u2b72\0\u2bb5\0\u014f\0\u2bf8\0\u014f"+
    "\0\u014f\0\u014f\0\u014f\0\u014f\0\u014f\0\u2c3b\0\u2c7e\0\u014f"+
    "\0\u2cc1\0\u2d04\0\u2d47\0\u2d8a\0\u014f\0\u2dcd\0\u2e10\0\u014f"+
    "\0\u014f\0\u2e53\0\u2e96\0\u2ed9\0\u2f1c\0\u014f\0\u2f5f\0\u2fa2"+
    "\0\u014f\0\u014f\0\u2fe5\0\u3028\0\u306b\0\103\0\u30ae\0\103";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[264];
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
    "\1\31\1\32\1\33\1\34\1\35\1\36\1\37\1\6"+
    "\1\40\1\41\2\6\1\42\1\43\1\6\1\44\1\45"+
    "\1\6\1\46\1\47\1\50\1\51\1\6\1\52\1\6"+
    "\1\53\1\54\1\55\1\56\1\57\1\60\1\6\1\61"+
    "\2\6\1\62\1\63\1\64\2\7\105\0\2\3\103\0"+
    "\1\65\21\0\1\66\61\0\1\7\1\67\1\0\1\7"+
    "\1\70\67\0\2\7\5\0\4\6\3\0\1\6\25\0"+
    "\41\6\5\0\1\7\2\0\1\7\1\70\67\0\2\7"+
    "\11\0\1\71\71\0\12\72\1\73\1\74\67\72\26\0"+
    "\1\75\102\0\1\76\102\0\1\77\1\0\1\100\100\0"+
    "\1\101\2\0\1\102\103\0\1\103\103\0\1\104\75\0"+
    "\1\105\102\0\1\106\102\0\1\107\102\0\1\110\116\0"+
    "\1\111\12\0\1\112\2\0\1\113\27\0\4\6\3\0"+
    "\1\6\25\0\22\6\1\114\4\6\1\115\2\6\1\116"+
    "\6\6\5\0\4\6\3\0\1\6\25\0\22\6\1\117"+
    "\2\6\1\120\13\6\5\0\4\6\3\0\1\6\25\0"+
    "\32\6\1\121\6\6\5\0\4\6\3\0\1\6\25\0"+
    "\10\6\1\122\12\6\1\123\1\6\1\124\13\6\5\0"+
    "\4\6\3\0\1\6\25\0\5\6\1\125\1\126\5\6"+
    "\1\127\10\6\1\130\13\6\5\0\4\6\3\0\1\6"+
    "\25\0\4\6\1\131\34\6\5\0\4\6\3\0\1\6"+
    "\25\0\10\6\1\132\30\6\5\0\4\6\3\0\1\6"+
    "\25\0\1\6\1\133\21\6\1\134\15\6\5\0\4\6"+
    "\3\0\1\6\25\0\23\6\1\135\15\6\5\0\4\6"+
    "\3\0\1\6\25\0\1\6\1\136\37\6\5\0\4\6"+
    "\3\0\1\6\25\0\24\6\1\137\14\6\5\0\4\6"+
    "\3\0\1\6\25\0\23\6\1\140\15\6\5\0\4\6"+
    "\3\0\1\6\25\0\6\6\1\141\1\142\31\6\5\0"+
    "\4\6\3\0\1\6\25\0\6\6\1\143\22\6\1\144"+
    "\7\6\5\0\4\6\3\0\1\6\25\0\14\6\1\145"+
    "\24\6\5\0\4\6\3\0\1\6\25\0\4\6\1\146"+
    "\15\6\1\147\1\6\1\150\14\6\5\0\4\6\3\0"+
    "\1\6\25\0\7\6\1\151\1\152\30\6\5\0\4\6"+
    "\3\0\1\6\25\0\4\6\1\153\3\6\1\154\14\6"+
    "\1\155\13\6\5\0\4\6\3\0\1\6\25\0\24\6"+
    "\1\156\14\6\5\0\4\6\3\0\1\6\25\0\25\6"+
    "\1\157\13\6\5\0\4\6\3\0\1\6\25\0\37\6"+
    "\1\160\1\6\1\65\2\0\100\65\5\0\1\161\1\0"+
    "\2\161\34\0\1\161\4\0\2\161\4\0\1\161\4\0"+
    "\1\161\2\0\2\161\1\0\1\161\3\0\1\161\1\0"+
    "\2\161\5\0\1\70\2\0\1\70\70\0\2\70\11\0"+
    "\1\162\71\0\1\72\2\0\100\72\43\0\1\163\115\0"+
    "\1\164\3\0\1\165\76\0\1\166\31\0\4\6\3\0"+
    "\1\6\25\0\25\6\1\167\13\6\5\0\4\6\3\0"+
    "\1\6\25\0\10\6\1\170\30\6\5\0\4\6\3\0"+
    "\1\6\25\0\22\6\1\171\1\6\1\172\14\6\5\0"+
    "\4\6\3\0\1\6\25\0\4\6\1\173\17\6\1\174"+
    "\14\6\5\0\4\6\3\0\1\6\25\0\26\6\1\175"+
    "\12\6\5\0\4\6\3\0\1\6\25\0\24\6\1\176"+
    "\14\6\5\0\4\6\3\0\1\6\25\0\30\6\1\177"+
    "\10\6\5\0\4\6\3\0\1\6\25\0\5\6\1\200"+
    "\33\6\5\0\4\6\3\0\1\6\25\0\7\6\1\201"+
    "\31\6\5\0\4\6\3\0\1\6\25\0\10\6\1\202"+
    "\30\6\5\0\4\6\3\0\1\6\25\0\20\6\1\203"+
    "\20\6\5\0\4\6\3\0\1\6\25\0\14\6\1\204"+
    "\3\6\1\205\20\6\5\0\4\6\3\0\1\6\25\0"+
    "\1\6\1\206\37\6\5\0\4\6\3\0\1\6\25\0"+
    "\25\6\1\207\13\6\5\0\4\6\3\0\1\6\25\0"+
    "\2\6\1\210\36\6\5\0\4\6\3\0\1\6\25\0"+
    "\10\6\1\211\11\6\1\212\16\6\5\0\4\6\3\0"+
    "\1\6\25\0\1\6\1\213\37\6\5\0\4\6\3\0"+
    "\1\6\25\0\1\6\1\214\37\6\5\0\4\6\3\0"+
    "\1\6\25\0\4\6\1\215\34\6\5\0\4\6\3\0"+
    "\1\6\25\0\23\6\1\216\15\6\5\0\4\6\3\0"+
    "\1\6\25\0\1\6\1\217\13\6\1\220\13\6\1\221"+
    "\7\6\5\0\4\6\3\0\1\6\25\0\25\6\1\222"+
    "\13\6\5\0\4\6\3\0\1\6\25\0\14\6\1\223"+
    "\24\6\5\0\4\6\3\0\1\6\25\0\1\6\1\224"+
    "\37\6\5\0\4\6\3\0\1\6\25\0\24\6\1\225"+
    "\14\6\5\0\4\6\3\0\1\6\25\0\4\6\1\226"+
    "\34\6\5\0\4\6\3\0\1\6\25\0\23\6\1\227"+
    "\15\6\5\0\4\6\3\0\1\6\25\0\6\6\1\230"+
    "\32\6\5\0\4\6\3\0\1\6\25\0\10\6\1\231"+
    "\30\6\5\0\4\6\3\0\1\6\25\0\14\6\1\232"+
    "\24\6\5\0\4\6\3\0\1\6\25\0\22\6\1\233"+
    "\16\6\5\0\4\6\3\0\1\6\25\0\7\6\1\234"+
    "\31\6\5\0\4\6\3\0\1\6\25\0\6\6\1\235"+
    "\32\6\5\0\4\6\3\0\1\6\25\0\14\6\1\236"+
    "\24\6\5\0\4\6\3\0\1\6\25\0\6\6\1\237"+
    "\32\6\5\0\4\6\3\0\1\6\25\0\40\6\1\240"+
    "\43\0\1\241\116\0\1\242\101\0\1\243\105\0\1\244"+
    "\26\0\4\6\3\0\1\6\25\0\23\6\1\245\15\6"+
    "\5\0\4\6\3\0\1\6\25\0\7\6\1\246\31\6"+
    "\5\0\4\6\3\0\1\6\25\0\4\6\1\247\34\6"+
    "\5\0\4\6\3\0\1\6\25\0\14\6\1\250\24\6"+
    "\5\0\4\6\3\0\1\6\25\0\1\6\1\251\37\6"+
    "\5\0\4\6\3\0\1\6\25\0\17\6\1\252\21\6"+
    "\5\0\4\6\3\0\1\6\25\0\7\6\1\253\31\6"+
    "\5\0\4\6\3\0\1\6\25\0\7\6\1\254\31\6"+
    "\5\0\4\6\3\0\1\6\25\0\23\6\1\255\15\6"+
    "\5\0\4\6\3\0\1\6\25\0\7\6\1\256\31\6"+
    "\5\0\4\6\3\0\1\6\25\0\24\6\1\257\14\6"+
    "\5\0\4\6\3\0\1\6\25\0\23\6\1\260\15\6"+
    "\5\0\4\6\3\0\1\6\25\0\6\6\1\261\32\6"+
    "\5\0\4\6\3\0\1\6\25\0\4\6\1\262\34\6"+
    "\5\0\4\6\3\0\1\6\25\0\26\6\1\263\12\6"+
    "\5\0\4\6\3\0\1\6\25\0\1\6\1\264\37\6"+
    "\5\0\4\6\3\0\1\6\25\0\24\6\1\265\1\266"+
    "\13\6\5\0\4\6\3\0\1\6\25\0\22\6\1\267"+
    "\16\6\5\0\4\6\3\0\1\6\25\0\7\6\1\270"+
    "\31\6\5\0\4\6\3\0\1\6\25\0\25\6\1\271"+
    "\13\6\5\0\4\6\3\0\1\6\25\0\24\6\1\272"+
    "\14\6\5\0\4\6\3\0\1\6\25\0\7\6\1\273"+
    "\31\6\5\0\4\6\3\0\1\6\25\0\30\6\1\274"+
    "\10\6\5\0\4\6\3\0\1\6\25\0\23\6\1\275"+
    "\15\6\5\0\4\6\3\0\1\6\25\0\6\6\1\276"+
    "\32\6\5\0\4\6\3\0\1\6\25\0\7\6\1\277"+
    "\31\6\5\0\4\6\3\0\1\6\25\0\10\6\1\300"+
    "\30\6\5\0\4\6\3\0\1\6\25\0\11\6\1\301"+
    "\27\6\5\0\4\6\3\0\1\6\25\0\14\6\1\302"+
    "\24\6\5\0\4\6\3\0\1\6\25\0\1\6\1\303"+
    "\37\6\5\0\4\6\3\0\1\6\25\0\14\6\1\304"+
    "\24\6\5\0\4\6\3\0\1\6\25\0\1\6\1\305"+
    "\37\6\5\0\4\6\3\0\1\6\25\0\27\6\1\306"+
    "\11\6\44\0\1\307\121\0\1\310\24\0\4\6\3\0"+
    "\1\6\25\0\7\6\1\311\31\6\5\0\4\6\3\0"+
    "\1\6\25\0\5\6\1\312\33\6\5\0\4\6\3\0"+
    "\1\6\25\0\23\6\1\313\15\6\5\0\4\6\3\0"+
    "\1\6\25\0\10\6\1\314\30\6\5\0\4\6\3\0"+
    "\1\6\25\0\24\6\1\315\14\6\5\0\4\6\3\0"+
    "\1\6\25\0\23\6\1\316\15\6\5\0\4\6\3\0"+
    "\1\6\25\0\14\6\1\317\24\6\5\0\4\6\3\0"+
    "\1\6\25\0\1\6\1\320\37\6\5\0\4\6\3\0"+
    "\1\6\25\0\22\6\1\321\16\6\5\0\4\6\3\0"+
    "\1\6\25\0\27\6\1\322\11\6\5\0\4\6\3\0"+
    "\1\6\25\0\7\6\1\323\31\6\5\0\4\6\3\0"+
    "\1\6\25\0\24\6\1\324\14\6\5\0\4\6\3\0"+
    "\1\6\25\0\6\6\1\325\32\6\5\0\4\6\3\0"+
    "\1\6\25\0\27\6\1\326\11\6\5\0\4\6\3\0"+
    "\1\6\25\0\11\6\1\327\27\6\5\0\4\6\3\0"+
    "\1\6\25\0\22\6\1\330\16\6\5\0\4\6\3\0"+
    "\1\6\25\0\14\6\1\331\24\6\5\0\4\6\3\0"+
    "\1\6\25\0\23\6\1\332\15\6\5\0\4\6\3\0"+
    "\1\6\25\0\24\6\1\333\14\6\5\0\4\6\3\0"+
    "\1\6\25\0\15\6\1\334\23\6\5\0\4\6\3\0"+
    "\1\6\25\0\33\6\1\335\5\6\5\0\4\6\3\0"+
    "\1\6\25\0\14\6\1\336\24\6\5\0\4\6\3\0"+
    "\1\6\25\0\23\6\1\337\15\6\5\0\4\6\3\0"+
    "\1\6\25\0\1\6\1\340\37\6\45\0\1\341\6\0"+
    "\1\342\77\0\1\343\36\0\4\6\3\0\1\6\25\0"+
    "\27\6\1\344\11\6\5\0\4\6\3\0\1\6\25\0"+
    "\1\6\1\345\37\6\5\0\4\6\3\0\1\6\25\0"+
    "\27\6\1\346\11\6\5\0\4\6\3\0\1\6\25\0"+
    "\2\6\1\347\36\6\5\0\4\6\3\0\1\6\25\0"+
    "\1\6\1\350\37\6\5\0\4\6\3\0\1\6\25\0"+
    "\23\6\1\351\15\6\5\0\4\6\3\0\1\6\25\0"+
    "\27\6\1\352\11\6\5\0\4\6\3\0\1\6\25\0"+
    "\15\6\1\353\23\6\5\0\4\6\3\0\1\6\25\0"+
    "\1\6\1\354\37\6\5\0\4\6\3\0\1\6\25\0"+
    "\6\6\1\355\32\6\5\0\4\6\3\0\1\6\25\0"+
    "\1\6\1\356\37\6\5\0\4\6\3\0\1\6\25\0"+
    "\27\6\1\357\11\6\5\0\4\6\3\0\1\6\25\0"+
    "\31\6\1\360\7\6\5\0\4\6\3\0\1\6\25\0"+
    "\4\6\1\361\34\6\46\0\1\362\100\0\1\363\43\0"+
    "\4\6\3\0\1\6\25\0\1\6\1\364\37\6\5\0"+
    "\4\6\3\0\1\6\25\0\23\6\1\365\15\6\5\0"+
    "\4\6\3\0\1\6\25\0\10\6\1\366\30\6\5\0"+
    "\4\6\3\0\1\6\25\0\23\6\1\367\15\6\5\0"+
    "\4\6\3\0\1\6\25\0\1\6\1\370\37\6\5\0"+
    "\4\6\3\0\1\6\25\0\22\6\1\371\16\6\47\0"+
    "\1\372\104\0\1\373\36\0\4\6\3\0\1\6\25\0"+
    "\23\6\1\374\15\6\5\0\4\6\3\0\1\6\25\0"+
    "\27\6\1\375\11\6\5\0\4\6\3\0\1\6\25\0"+
    "\22\6\1\376\16\6\50\0\1\377\100\0\1\u0100\41\0"+
    "\4\6\3\0\1\6\25\0\11\6\1\u0101\27\6\5\0"+
    "\4\6\3\0\1\6\25\0\23\6\1\u0102\15\6\51\0"+
    "\1\u0103\103\0\1\u0104\76\0\1\u0105\107\0\1\u0106\101\0"+
    "\1\u0107\103\0\1\u0108\27\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[12529];
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
    "\1\0\1\11\7\1\12\11\12\1\1\11\27\1\1\11"+
    "\1\0\2\1\1\0\1\11\1\0\14\11\3\0\46\1"+
    "\1\11\4\0\52\1\1\0\1\11\1\0\1\11\43\1"+
    "\1\0\30\1\2\0\1\11\16\1\2\0\6\1\2\0"+
    "\3\1\2\0\2\1\3\0\1\11\1\0\1\11";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[264];
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
          case 97: break;
          case 2: 
            { return WHITE_SPACE;
            } 
            // fall through
          case 98: break;
          case 3: 
            { return QUOTIENT;
            } 
            // fall through
          case 99: break;
          case 4: 
            { return DIGIT_CONTENT;
            } 
            // fall through
          case 100: break;
          case 5: 
            { return ID_CONTENT;
            } 
            // fall through
          case 101: break;
          case 6: 
            { return DOT;
            } 
            // fall through
          case 102: break;
          case 7: 
            { return QUOTE;
            } 
            // fall through
          case 103: break;
          case 8: 
            { return BACK;
            } 
            // fall through
          case 104: break;
          case 9: 
            { return LBRACE;
            } 
            // fall through
          case 105: break;
          case 10: 
            { return RBRACE;
            } 
            // fall through
          case 106: break;
          case 11: 
            { return LBRACK;
            } 
            // fall through
          case 107: break;
          case 12: 
            { return RBRACK;
            } 
            // fall through
          case 108: break;
          case 13: 
            { return LPAREN;
            } 
            // fall through
          case 109: break;
          case 14: 
            { return RPAREN;
            } 
            // fall through
          case 110: break;
          case 15: 
            { return COLON;
            } 
            // fall through
          case 111: break;
          case 16: 
            { return SEMI;
            } 
            // fall through
          case 112: break;
          case 17: 
            { return COMMA;
            } 
            // fall through
          case 113: break;
          case 18: 
            { return ASSIGN;
            } 
            // fall through
          case 114: break;
          case 19: 
            { return NOT;
            } 
            // fall through
          case 115: break;
          case 20: 
            { return PLUS;
            } 
            // fall through
          case 116: break;
          case 21: 
            { return MINUS;
            } 
            // fall through
          case 117: break;
          case 22: 
            { return LESS;
            } 
            // fall through
          case 118: break;
          case 23: 
            { return MUL;
            } 
            // fall through
          case 119: break;
          case 24: 
            { return REMAINDER;
            } 
            // fall through
          case 120: break;
          case 25: 
            { return GREATER;
            } 
            // fall through
          case 121: break;
          case 26: 
            { return APOS;
            } 
            // fall through
          case 122: break;
          case 27: 
            { return COMMENT;
            } 
            // fall through
          case 123: break;
          case 28: 
            { return QUOTIENT_ASSIGN;
            } 
            // fall through
          case 124: break;
          case 29: 
            { return CONCAT;
            } 
            // fall through
          case 125: break;
          case 30: 
            { return STRING_CONTENT;
            } 
            // fall through
          case 126: break;
          case 31: 
            { return EQ;
            } 
            // fall through
          case 127: break;
          case 32: 
            { return NOT_EQ;
            } 
            // fall through
          case 128: break;
          case 33: 
            { return PLUS_ASSIGN;
            } 
            // fall through
          case 129: break;
          case 34: 
            { return PLUS_PLUS;
            } 
            // fall through
          case 130: break;
          case 35: 
            { return MINUS_ASSIGN;
            } 
            // fall through
          case 131: break;
          case 36: 
            { return MINUS_MINUS;
            } 
            // fall through
          case 132: break;
          case 37: 
            { return COND_OR;
            } 
            // fall through
          case 133: break;
          case 38: 
            { return COND_AND;
            } 
            // fall through
          case 134: break;
          case 39: 
            { return LESS_OR_EQUAL;
            } 
            // fall through
          case 135: break;
          case 40: 
            { return MUL_ASSIGN;
            } 
            // fall through
          case 136: break;
          case 41: 
            { return REMAINDER_ASSIGN;
            } 
            // fall through
          case 137: break;
          case 42: 
            { return GREATER_OR_EQUAL;
            } 
            // fall through
          case 138: break;
          case 43: 
            { return DO;
            } 
            // fall through
          case 139: break;
          case 44: 
            { return IN;
            } 
            // fall through
          case 140: break;
          case 45: 
            { return IF;
            } 
            // fall through
          case 141: break;
          case 46: 
            { return TYPE_TAIL;
            } 
            // fall through
          case 142: break;
          case 47: 
            { return NEW;
            } 
            // fall through
          case 143: break;
          case 48: 
            { return ANY;
            } 
            // fall through
          case 144: break;
          case 49: 
            { return MAP;
            } 
            // fall through
          case 145: break;
          case 50: 
            { return SET;
            } 
            // fall through
          case 146: break;
          case 51: 
            { return GET;
            } 
            // fall through
          case 147: break;
          case 52: 
            { return INT;
            } 
            // fall through
          case 148: break;
          case 53: 
            { return FOR;
            } 
            // fall through
          case 149: break;
          case 54: 
            { return FUN;
            } 
            // fall through
          case 150: break;
          case 55: 
            { return I64;
            } 
            // fall through
          case 151: break;
          case 56: 
            { return MSG;
            } 
            // fall through
          case 152: break;
          case 57: 
            { return CSV;
            } 
            // fall through
          case 153: break;
          case 58: 
            { return TRUE;
            } 
            // fall through
          case 154: break;
          case 59: 
            { return THIS;
            } 
            // fall through
          case 155: break;
          case 60: 
            { return NULL;
            } 
            // fall through
          case 156: break;
          case 61: 
            { return AUTO;
            } 
            // fall through
          case 157: break;
          case 62: 
            { return CTOR;
            } 
            // fall through
          case 158: break;
          case 63: 
            { return ENUM;
            } 
            // fall through
          case 159: break;
          case 64: 
            { return ELSE;
            } 
            // fall through
          case 160: break;
          case 65: 
            { return BOOL;
            } 
            // fall through
          case 161: break;
          case 66: 
            { return BIND;
            } 
            // fall through
          case 162: break;
          case 67: 
            { return CAST;
            } 
            // fall through
          case 163: break;
          case 68: 
            { return LIST;
            } 
            // fall through
          case 164: break;
          case 69: 
            { return HTTP;
            } 
            // fall through
          case 165: break;
          case 70: 
            { return TCALL;
            } 
            // fall through
          case 166: break;
          case 71: 
            { return THROW;
            } 
            // fall through
          case 167: break;
          case 72: 
            { return WHILE;
            } 
            // fall through
          case 168: break;
          case 73: 
            { return AWAIT;
            } 
            // fall through
          case 169: break;
          case 74: 
            { return ASYNC;
            } 
            // fall through
          case 170: break;
          case 75: 
            { return YIELD;
            } 
            // fall through
          case 171: break;
          case 76: 
            { return USING;
            } 
            // fall through
          case 172: break;
          case 77: 
            { return BREAK;
            } 
            // fall through
          case 173: break;
          case 78: 
            { return CLASS;
            } 
            // fall through
          case 174: break;
          case 79: 
            { return FALSE;
            } 
            // fall through
          case 175: break;
          case 80: 
            { return MYSQL;
            } 
            // fall through
          case 176: break;
          case 81: 
            { return PUBLIC;
            } 
            // fall through
          case 177: break;
          case 82: 
            { return ASSERT;
            } 
            // fall through
          case 178: break;
          case 83: 
            { return DOUBLE;
            } 
            // fall through
          case 179: break;
          case 84: 
            { return STATIC;
            } 
            // fall through
          case 180: break;
          case 85: 
            { return STRING;
            } 
            // fall through
          case 181: break;
          case 86: 
            { return STRUCT;
            } 
            // fall through
          case 182: break;
          case 87: 
            { return RETURN;
            } 
            // fall through
          case 183: break;
          case 88: 
            { return ELSEIF;
            } 
            // fall through
          case 184: break;
          case 89: 
            { return PRIVATE;
            } 
            // fall through
          case 185: break;
          case 90: 
            { return REFLECT;
            } 
            // fall through
          case 186: break;
          case 91: 
            { return FUNCTOR;
            } 
            // fall through
          case 187: break;
          case 92: 
            { return REGISTER;
            } 
            // fall through
          case 188: break;
          case 93: 
            { return PROTECTED;
            } 
            // fall through
          case 189: break;
          case 94: 
            { return NAMESPACE;
            } 
            // fall through
          case 190: break;
          case 95: 
            { return HTTP_UPLOAD;
            } 
            // fall through
          case 191: break;
          case 96: 
            { return HTTP_DOWNLOAD;
            } 
            // fall through
          case 192: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}

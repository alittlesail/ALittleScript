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
    "\11\0\1\3\4\2\22\0\1\3\1\30\1\13\2\0\1\36\1\34\1\40\1\22\1\23\1\5\1\31\1\26"+
    "\1\32\1\12\1\4\1\6\11\11\1\24\1\25\1\35\1\27\1\37\2\0\2\10\1\67\1\64\1\10"+
    "\1\76\1\15\1\63\3\15\1\72\1\66\1\71\6\15\1\65\5\15\1\20\1\14\1\21\1\0\1\15"+
    "\1\0\1\55\1\51\1\53\1\57\1\42\1\73\1\43\1\74\1\44\1\15\1\75\1\52\1\70\1\61"+
    "\1\56\1\47\1\15\1\41\1\45\1\46\1\50\1\54\1\62\1\7\1\60\1\15\1\16\1\33\1\17"+
    "\7\0\1\1\242\0\2\1\26\0");

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\1\0\1\1\1\2\1\3\1\4\1\5\1\6\1\5"+
    "\1\7\1\10\1\11\1\12\1\13\1\14\1\15\1\16"+
    "\1\17\1\20\1\21\1\22\1\23\1\24\1\25\1\26"+
    "\2\1\1\27\1\30\1\31\1\32\31\6\1\33\1\0"+
    "\1\34\1\35\1\0\1\5\1\36\1\0\1\37\1\0"+
    "\1\40\1\41\1\42\1\43\1\44\1\45\1\46\1\47"+
    "\1\50\1\51\1\52\4\6\1\53\1\54\17\6\1\55"+
    "\5\6\1\56\22\6\3\0\1\5\1\57\5\6\1\60"+
    "\1\61\1\62\22\6\1\63\2\6\1\64\4\6\1\65"+
    "\4\6\1\66\1\67\1\6\1\70\4\6\1\71\1\6"+
    "\1\72\1\6\1\73\3\6\1\74\1\75\3\6\1\76"+
    "\2\6\1\77\5\6\1\100\1\101\1\102\1\103\1\6"+
    "\1\104\10\6\1\105\2\6\1\106\3\6\1\107\12\6"+
    "\1\110\1\111\3\6\1\112\1\113\1\114\1\115\2\6"+
    "\1\116\1\117\2\6\1\120\1\6\1\121\6\6\1\122"+
    "\2\6\1\123\1\6\1\124\1\125\1\126\1\127\2\6"+
    "\1\130\1\6\1\131\1\132\1\133\5\6\1\134\3\6"+
    "\1\135\1\136\10\6\1\137\1\140\1\6\1\141\3\6"+
    "\1\142\1\143\1\144\1\145\1\146\3\6\1\147\1\6"+
    "\1\150";

  private static int [] zzUnpackAction() {
    int [] result = new int[305];
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
    "\0\0\0\77\0\176\0\275\0\374\0\u013b\0\u017a\0\u01b9"+
    "\0\u01f8\0\u0237\0\77\0\77\0\77\0\77\0\77\0\77"+
    "\0\77\0\77\0\77\0\77\0\u0276\0\u02b5\0\u02f4\0\u0333"+
    "\0\u0372\0\u03b1\0\u03f0\0\u042f\0\u046e\0\77\0\u04ad\0\u04ec"+
    "\0\u052b\0\u056a\0\u05a9\0\u05e8\0\u0627\0\u0666\0\u06a5\0\u06e4"+
    "\0\u0723\0\u0762\0\u07a1\0\u07e0\0\u081f\0\u085e\0\u089d\0\u08dc"+
    "\0\u091b\0\u095a\0\u0999\0\u09d8\0\u0a17\0\u0a56\0\u0a95\0\u0ad4"+
    "\0\u0b13\0\77\0\77\0\u0b52\0\u0b91\0\u0bd0\0\u0237\0\77"+
    "\0\u0c0f\0\77\0\77\0\77\0\77\0\77\0\77\0\77"+
    "\0\77\0\77\0\77\0\77\0\u0c4e\0\u0c8d\0\u0ccc\0\u0d0b"+
    "\0\u0d4a\0\u017a\0\u0d89\0\u0dc8\0\u0e07\0\u0e46\0\u0e85\0\u0ec4"+
    "\0\u0f03\0\u0f42\0\u0f81\0\u0fc0\0\u0fff\0\u103e\0\u107d\0\u10bc"+
    "\0\u10fb\0\u113a\0\u1179\0\u11b8\0\u11f7\0\u1236\0\u1275\0\u12b4"+
    "\0\u12f3\0\u1332\0\u1371\0\u13b0\0\u13ef\0\u142e\0\u146d\0\u14ac"+
    "\0\u14eb\0\u152a\0\u1569\0\u15a8\0\u15e7\0\u1626\0\u1665\0\u16a4"+
    "\0\u16e3\0\u1722\0\u1761\0\u17a0\0\u17df\0\u0b52\0\77\0\u181e"+
    "\0\u185d\0\u189c\0\u18db\0\u191a\0\u017a\0\u017a\0\u017a\0\u1959"+
    "\0\u1998\0\u19d7\0\u1a16\0\u1a55\0\u1a94\0\u1ad3\0\u1b12\0\u1b51"+
    "\0\u1b90\0\u1bcf\0\u1c0e\0\u1c4d\0\u1c8c\0\u1ccb\0\u1d0a\0\u1d49"+
    "\0\u1d88\0\u017a\0\u1dc7\0\u1e06\0\u017a\0\u1e45\0\u1e84\0\u1ec3"+
    "\0\u1f02\0\u017a\0\u1f41\0\u1f80\0\u1fbf\0\u1ffe\0\u017a\0\u017a"+
    "\0\u203d\0\u017a\0\u207c\0\u20bb\0\u20fa\0\u2139\0\u017a\0\u2178"+
    "\0\u017a\0\u21b7\0\77\0\u21f6\0\u2235\0\u2274\0\u22b3\0\u017a"+
    "\0\u22f2\0\u2331\0\u2370\0\u017a\0\u23af\0\u23ee\0\u017a\0\u242d"+
    "\0\u246c\0\u24ab\0\u24ea\0\u2529\0\u017a\0\u017a\0\u017a\0\u017a"+
    "\0\u2568\0\u017a\0\u25a7\0\u25e6\0\u2625\0\u2664\0\u26a3\0\u26e2"+
    "\0\u2721\0\u2760\0\u017a\0\u279f\0\u27de\0\u281d\0\u285c\0\u289b"+
    "\0\u28da\0\u017a\0\u2919\0\u2958\0\u2997\0\u29d6\0\u2a15\0\u2a54"+
    "\0\u2a93\0\u2ad2\0\u2b11\0\u2b50\0\u017a\0\u017a\0\u2b8f\0\u2bce"+
    "\0\u2c0d\0\u017a\0\u017a\0\u017a\0\u017a\0\u2c4c\0\u2c8b\0\u017a"+
    "\0\u017a\0\u2cca\0\u2d09\0\u017a\0\u2d48\0\u017a\0\u2d87\0\u2dc6"+
    "\0\u2e05\0\u2e44\0\u2e83\0\u2ec2\0\u017a\0\u2f01\0\u2f40\0\u017a"+
    "\0\u2f7f\0\u017a\0\u017a\0\u017a\0\u017a\0\u2fbe\0\u2ffd\0\u017a"+
    "\0\u303c\0\u017a\0\u017a\0\u017a\0\u307b\0\u30ba\0\u30f9\0\u3138"+
    "\0\u3177\0\u017a\0\u31b6\0\u31f5\0\u3234\0\u017a\0\u017a\0\u3273"+
    "\0\u32b2\0\u32f1\0\u3330\0\u336f\0\u33ae\0\u33ed\0\u342c\0\u017a"+
    "\0\u017a\0\u346b\0\u017a\0\u34aa\0\u34e9\0\u3528\0\u017a\0\u017a"+
    "\0\u017a\0\u017a\0\u017a\0\u3567\0\u35a6\0\u35e5\0\u017a\0\u3624"+
    "\0\u017a";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[305];
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
    "\2\2\2\3\1\4\1\5\1\6\2\7\1\10\1\11"+
    "\1\12\1\13\1\7\1\14\1\15\1\16\1\17\1\20"+
    "\1\21\1\22\1\23\1\24\1\25\1\26\1\27\1\30"+
    "\1\31\1\32\1\33\1\34\1\35\1\36\1\37\1\40"+
    "\1\41\1\42\1\43\1\44\1\45\1\46\1\47\1\50"+
    "\1\51\1\52\1\53\1\54\1\55\1\56\1\57\1\60"+
    "\1\61\2\7\1\62\1\63\1\7\1\64\1\65\1\66"+
    "\2\7\1\67\101\0\2\3\77\0\1\70\1\71\21\0"+
    "\1\72\76\0\1\73\55\0\1\10\1\74\1\0\1\10"+
    "\1\75\72\0\4\7\3\0\1\7\23\0\36\7\6\0"+
    "\1\10\2\0\1\10\1\75\76\0\1\76\64\0\13\77"+
    "\1\100\1\101\62\77\27\0\1\102\76\0\1\103\76\0"+
    "\1\104\1\0\1\105\74\0\1\106\2\0\1\107\77\0"+
    "\1\110\77\0\1\111\71\0\1\112\76\0\1\113\76\0"+
    "\1\114\55\0\4\7\3\0\1\7\23\0\1\7\1\115"+
    "\34\7\6\0\4\7\3\0\1\7\23\0\11\7\1\116"+
    "\6\7\1\117\15\7\6\0\4\7\3\0\1\7\23\0"+
    "\1\7\1\120\34\7\6\0\4\7\3\0\1\7\23\0"+
    "\20\7\1\121\11\7\1\122\3\7\6\0\4\7\3\0"+
    "\1\7\23\0\1\7\1\123\3\7\1\124\30\7\6\0"+
    "\4\7\3\0\1\7\23\0\1\125\11\7\1\126\20\7"+
    "\1\127\2\7\6\0\4\7\3\0\1\7\23\0\1\130"+
    "\6\7\1\131\26\7\6\0\4\7\3\0\1\7\23\0"+
    "\4\7\1\132\31\7\6\0\4\7\3\0\1\7\23\0"+
    "\1\133\2\7\1\134\11\7\1\135\20\7\6\0\4\7"+
    "\3\0\1\7\23\0\15\7\1\136\20\7\6\0\4\7"+
    "\3\0\1\7\23\0\5\7\1\137\3\7\1\140\2\7"+
    "\1\141\1\142\20\7\6\0\4\7\3\0\1\7\23\0"+
    "\14\7\1\143\21\7\6\0\4\7\3\0\1\7\23\0"+
    "\4\7\1\144\13\7\1\145\1\146\14\7\6\0\4\7"+
    "\3\0\1\7\23\0\6\7\1\147\27\7\6\0\4\7"+
    "\3\0\1\7\23\0\15\7\1\150\20\7\6\0\4\7"+
    "\3\0\1\7\23\0\3\7\1\151\32\7\6\0\4\7"+
    "\3\0\1\7\23\0\1\7\1\152\5\7\1\153\4\7"+
    "\1\154\21\7\6\0\4\7\3\0\1\7\23\0\33\7"+
    "\1\155\2\7\6\0\4\7\3\0\1\7\23\0\5\7"+
    "\1\156\30\7\6\0\4\7\3\0\1\7\23\0\4\7"+
    "\1\157\7\7\1\160\21\7\6\0\4\7\3\0\1\7"+
    "\23\0\15\7\1\161\11\7\1\162\6\7\6\0\4\7"+
    "\3\0\1\7\23\0\7\7\1\163\4\7\1\164\21\7"+
    "\6\0\4\7\3\0\1\7\23\0\3\7\1\165\10\7"+
    "\1\166\21\7\6\0\4\7\3\0\1\7\23\0\7\7"+
    "\1\167\4\7\1\170\1\171\20\7\6\0\4\7\3\0"+
    "\1\7\23\0\7\7\1\172\26\7\1\70\2\0\74\70"+
    "\4\173\1\174\1\175\71\173\6\0\1\176\1\0\2\176"+
    "\30\0\1\176\6\0\1\176\1\0\1\176\1\0\1\176"+
    "\1\0\1\176\4\0\1\176\2\0\1\176\3\0\1\176"+
    "\2\0\1\176\6\0\1\75\2\0\1\75\77\0\1\177"+
    "\64\0\1\77\2\0\74\77\6\0\4\7\3\0\1\7"+
    "\23\0\2\7\1\200\2\7\1\201\24\7\1\202\3\7"+
    "\6\0\4\7\3\0\1\7\23\0\4\7\1\203\31\7"+
    "\6\0\4\7\3\0\1\7\23\0\7\7\1\204\26\7"+
    "\6\0\4\7\3\0\1\7\23\0\5\7\1\205\30\7"+
    "\6\0\4\7\3\0\1\7\23\0\5\7\1\206\30\7"+
    "\6\0\4\7\3\0\1\7\23\0\5\7\1\207\30\7"+
    "\6\0\4\7\3\0\1\7\23\0\1\210\13\7\1\211"+
    "\21\7\6\0\4\7\3\0\1\7\23\0\7\7\1\212"+
    "\26\7\6\0\4\7\3\0\1\7\23\0\14\7\1\213"+
    "\21\7\6\0\4\7\3\0\1\7\23\0\1\214\2\7"+
    "\1\215\32\7\6\0\4\7\3\0\1\7\23\0\3\7"+
    "\1\216\11\7\1\217\20\7\6\0\4\7\3\0\1\7"+
    "\23\0\10\7\1\220\25\7\6\0\4\7\3\0\1\7"+
    "\23\0\3\7\1\221\32\7\6\0\4\7\3\0\1\7"+
    "\23\0\1\7\1\222\34\7\6\0\4\7\3\0\1\7"+
    "\23\0\20\7\1\223\15\7\6\0\4\7\3\0\1\7"+
    "\23\0\15\7\1\224\20\7\6\0\4\7\3\0\1\7"+
    "\23\0\20\7\1\225\15\7\6\0\4\7\3\0\1\7"+
    "\23\0\15\7\1\226\20\7\6\0\4\7\3\0\1\7"+
    "\23\0\14\7\1\227\21\7\6\0\4\7\3\0\1\7"+
    "\23\0\4\7\1\230\31\7\6\0\4\7\3\0\1\7"+
    "\23\0\20\7\1\231\15\7\6\0\4\7\3\0\1\7"+
    "\23\0\1\232\35\7\6\0\4\7\3\0\1\7\23\0"+
    "\4\7\1\233\12\7\1\234\16\7\6\0\4\7\3\0"+
    "\1\7\23\0\17\7\1\235\16\7\6\0\4\7\3\0"+
    "\1\7\23\0\14\7\1\236\21\7\6\0\4\7\3\0"+
    "\1\7\23\0\5\7\1\237\30\7\6\0\4\7\3\0"+
    "\1\7\23\0\7\7\1\240\26\7\6\0\4\7\3\0"+
    "\1\7\23\0\1\7\1\241\34\7\6\0\4\7\3\0"+
    "\1\7\23\0\21\7\1\242\14\7\6\0\4\7\3\0"+
    "\1\7\23\0\11\7\1\243\24\7\6\0\4\7\3\0"+
    "\1\7\23\0\27\7\1\244\6\7\6\0\4\7\3\0"+
    "\1\7\23\0\3\7\1\245\32\7\6\0\4\7\3\0"+
    "\1\7\23\0\5\7\1\246\30\7\6\0\4\7\3\0"+
    "\1\7\23\0\2\7\1\247\33\7\6\0\4\7\3\0"+
    "\1\7\23\0\6\7\1\250\27\7\6\0\4\7\3\0"+
    "\1\7\23\0\20\7\1\251\15\7\6\0\4\7\3\0"+
    "\1\7\23\0\16\7\1\252\17\7\6\0\4\7\3\0"+
    "\1\7\23\0\11\7\1\253\24\7\6\0\4\7\3\0"+
    "\1\7\23\0\5\7\1\254\30\7\6\0\4\7\3\0"+
    "\1\7\23\0\4\7\1\255\31\7\6\0\4\7\3\0"+
    "\1\7\23\0\20\7\1\256\15\7\6\0\4\7\3\0"+
    "\1\7\23\0\20\7\1\257\15\7\6\0\4\7\3\0"+
    "\1\7\23\0\11\7\1\260\24\7\6\0\4\7\3\0"+
    "\1\7\23\0\1\261\35\7\6\0\4\7\3\0\1\7"+
    "\23\0\20\7\1\262\15\7\5\173\1\175\71\173\4\0"+
    "\1\71\72\0\4\71\1\263\72\71\6\0\4\7\3\0"+
    "\1\7\23\0\3\7\1\264\32\7\6\0\4\7\3\0"+
    "\1\7\23\0\7\7\1\265\26\7\6\0\4\7\3\0"+
    "\1\7\23\0\11\7\1\266\24\7\6\0\4\7\3\0"+
    "\1\7\23\0\1\7\1\267\34\7\6\0\4\7\3\0"+
    "\1\7\23\0\27\7\1\270\6\7\6\0\4\7\3\0"+
    "\1\7\23\0\3\7\1\271\3\7\1\272\26\7\6\0"+
    "\4\7\3\0\1\7\23\0\5\7\1\273\30\7\6\0"+
    "\4\7\3\0\1\7\23\0\1\7\1\274\34\7\6\0"+
    "\4\7\3\0\1\7\23\0\11\7\1\275\24\7\6\0"+
    "\4\7\3\0\1\7\23\0\15\7\1\276\20\7\6\0"+
    "\4\7\3\0\1\7\23\0\4\7\1\277\31\7\6\0"+
    "\4\7\3\0\1\7\23\0\13\7\1\300\22\7\6\0"+
    "\4\7\3\0\1\7\23\0\5\7\1\301\30\7\6\0"+
    "\4\7\3\0\1\7\23\0\11\7\1\302\24\7\6\0"+
    "\4\7\3\0\1\7\23\0\20\7\1\303\15\7\6\0"+
    "\4\7\3\0\1\7\23\0\14\7\1\304\21\7\6\0"+
    "\4\7\3\0\1\7\23\0\16\7\1\305\17\7\6\0"+
    "\4\7\3\0\1\7\23\0\11\7\1\306\24\7\6\0"+
    "\4\7\3\0\1\7\23\0\2\7\1\307\33\7\6\0"+
    "\4\7\3\0\1\7\23\0\1\310\35\7\6\0\4\7"+
    "\3\0\1\7\23\0\4\7\1\311\31\7\6\0\4\7"+
    "\3\0\1\7\23\0\5\7\1\312\30\7\6\0\4\7"+
    "\3\0\1\7\23\0\4\7\1\313\1\314\30\7\6\0"+
    "\4\7\3\0\1\7\23\0\1\7\1\315\34\7\6\0"+
    "\4\7\3\0\1\7\23\0\20\7\1\316\15\7\6\0"+
    "\4\7\3\0\1\7\23\0\3\7\1\317\32\7\6\0"+
    "\4\7\3\0\1\7\23\0\3\7\1\320\32\7\6\0"+
    "\4\7\3\0\1\7\23\0\10\7\1\321\25\7\6\0"+
    "\4\7\3\0\1\7\23\0\11\7\1\322\24\7\6\0"+
    "\4\7\3\0\1\7\23\0\11\7\1\323\24\7\6\0"+
    "\4\7\3\0\1\7\23\0\1\7\1\324\34\7\6\0"+
    "\4\7\3\0\1\7\23\0\11\7\1\325\24\7\6\0"+
    "\4\7\3\0\1\7\23\0\6\7\1\326\27\7\6\0"+
    "\4\7\3\0\1\7\23\0\4\7\1\327\31\7\6\0"+
    "\4\7\3\0\1\7\23\0\11\7\1\330\24\7\6\0"+
    "\4\7\3\0\1\7\23\0\3\7\1\331\32\7\6\0"+
    "\4\7\3\0\1\7\23\0\5\7\1\332\30\7\6\0"+
    "\4\7\3\0\1\7\23\0\2\7\1\333\33\7\6\0"+
    "\4\7\3\0\1\7\23\0\4\7\1\334\31\7\6\0"+
    "\4\7\3\0\1\7\23\0\12\7\1\335\23\7\6\0"+
    "\4\7\3\0\1\7\23\0\4\7\1\336\31\7\6\0"+
    "\4\7\3\0\1\7\23\0\1\337\35\7\6\0\4\7"+
    "\3\0\1\7\23\0\1\7\1\340\34\7\6\0\4\7"+
    "\3\0\1\7\23\0\3\7\1\341\32\7\6\0\4\7"+
    "\3\0\1\7\23\0\20\7\1\342\15\7\6\0\4\7"+
    "\3\0\1\7\23\0\12\7\1\343\23\7\6\0\4\7"+
    "\3\0\1\7\23\0\3\7\1\344\32\7\6\0\4\7"+
    "\3\0\1\7\23\0\11\7\1\345\24\7\6\0\4\7"+
    "\3\0\1\7\23\0\21\7\1\346\14\7\6\0\4\7"+
    "\3\0\1\7\23\0\14\7\1\347\21\7\6\0\4\7"+
    "\3\0\1\7\23\0\1\7\1\350\34\7\6\0\4\7"+
    "\3\0\1\7\23\0\3\7\1\351\32\7\6\0\4\7"+
    "\3\0\1\7\23\0\2\7\1\352\33\7\6\0\4\7"+
    "\3\0\1\7\23\0\34\7\1\353\1\7\6\0\4\7"+
    "\3\0\1\7\23\0\4\7\1\354\31\7\6\0\4\7"+
    "\3\0\1\7\23\0\5\7\1\355\30\7\6\0\4\7"+
    "\3\0\1\7\23\0\3\7\1\356\32\7\6\0\4\7"+
    "\3\0\1\7\23\0\1\357\35\7\6\0\4\7\3\0"+
    "\1\7\23\0\12\7\1\360\23\7\6\0\4\7\3\0"+
    "\1\7\23\0\5\7\1\361\30\7\6\0\4\7\3\0"+
    "\1\7\23\0\15\7\1\362\20\7\6\0\4\7\3\0"+
    "\1\7\23\0\11\7\1\363\24\7\6\0\4\7\3\0"+
    "\1\7\23\0\16\7\1\364\17\7\6\0\4\7\3\0"+
    "\1\7\23\0\4\7\1\365\31\7\6\0\4\7\3\0"+
    "\1\7\23\0\1\7\1\366\34\7\6\0\4\7\3\0"+
    "\1\7\23\0\23\7\1\367\1\370\11\7\6\0\4\7"+
    "\3\0\1\7\23\0\5\7\1\371\30\7\6\0\4\7"+
    "\3\0\1\7\23\0\14\7\1\372\21\7\6\0\4\7"+
    "\3\0\1\7\23\0\13\7\1\373\22\7\6\0\4\7"+
    "\3\0\1\7\23\0\7\7\1\374\26\7\6\0\4\7"+
    "\3\0\1\7\23\0\1\7\1\375\34\7\6\0\4\7"+
    "\3\0\1\7\23\0\5\7\1\376\30\7\6\0\4\7"+
    "\3\0\1\7\23\0\5\7\1\377\30\7\6\0\4\7"+
    "\3\0\1\7\23\0\20\7\1\u0100\15\7\6\0\4\7"+
    "\3\0\1\7\23\0\12\7\1\u0101\23\7\6\0\4\7"+
    "\3\0\1\7\23\0\32\7\1\u0102\3\7\6\0\4\7"+
    "\3\0\1\7\23\0\2\7\1\u0103\33\7\6\0\4\7"+
    "\3\0\1\7\23\0\5\7\1\u0104\30\7\6\0\4\7"+
    "\3\0\1\7\23\0\12\7\1\u0105\23\7\6\0\4\7"+
    "\3\0\1\7\23\0\5\7\1\u0106\30\7\6\0\4\7"+
    "\3\0\1\7\23\0\12\7\1\u0107\23\7\6\0\4\7"+
    "\3\0\1\7\23\0\12\7\1\u0108\23\7\6\0\4\7"+
    "\3\0\1\7\23\0\20\7\1\u0109\15\7\6\0\4\7"+
    "\3\0\1\7\23\0\5\7\1\u010a\30\7\6\0\4\7"+
    "\3\0\1\7\23\0\20\7\1\u010b\15\7\6\0\4\7"+
    "\3\0\1\7\23\0\1\7\1\u010c\34\7\6\0\4\7"+
    "\3\0\1\7\23\0\6\7\1\u010d\27\7\6\0\4\7"+
    "\3\0\1\7\23\0\15\7\1\u010e\20\7\6\0\4\7"+
    "\3\0\1\7\23\0\6\7\1\u010f\27\7\6\0\4\7"+
    "\3\0\1\7\23\0\14\7\1\u0110\21\7\6\0\4\7"+
    "\3\0\1\7\23\0\10\7\1\u0111\25\7\6\0\4\7"+
    "\3\0\1\7\23\0\1\7\1\u0112\34\7\6\0\4\7"+
    "\3\0\1\7\23\0\14\7\1\u0113\21\7\6\0\4\7"+
    "\3\0\1\7\23\0\15\7\1\u0114\20\7\6\0\4\7"+
    "\3\0\1\7\23\0\1\7\1\u0115\34\7\6\0\4\7"+
    "\3\0\1\7\23\0\5\7\1\u0116\30\7\6\0\4\7"+
    "\3\0\1\7\23\0\1\7\1\u0117\34\7\6\0\4\7"+
    "\3\0\1\7\23\0\5\7\1\u0118\30\7\6\0\4\7"+
    "\3\0\1\7\23\0\7\7\1\u0119\26\7\6\0\4\7"+
    "\3\0\1\7\23\0\14\7\1\u011a\21\7\6\0\4\7"+
    "\3\0\1\7\23\0\21\7\1\u011b\14\7\6\0\4\7"+
    "\3\0\1\7\23\0\11\7\1\u011c\24\7\6\0\4\7"+
    "\3\0\1\7\23\0\20\7\1\u011d\15\7\6\0\4\7"+
    "\3\0\1\7\23\0\11\7\1\u011e\24\7\6\0\4\7"+
    "\3\0\1\7\23\0\2\7\1\u011f\33\7\6\0\4\7"+
    "\3\0\1\7\23\0\1\u0120\35\7\6\0\4\7\3\0"+
    "\1\7\23\0\1\u0121\35\7\6\0\4\7\3\0\1\7"+
    "\23\0\1\7\1\u0122\34\7\6\0\4\7\3\0\1\7"+
    "\23\0\1\7\1\u0123\34\7\6\0\4\7\3\0\1\7"+
    "\23\0\12\7\1\u0124\23\7\6\0\4\7\3\0\1\7"+
    "\23\0\20\7\1\u0125\15\7\6\0\4\7\3\0\1\7"+
    "\23\0\15\7\1\u0126\20\7\6\0\4\7\3\0\1\7"+
    "\23\0\5\7\1\u0127\30\7\6\0\4\7\3\0\1\7"+
    "\23\0\1\7\1\u0128\34\7\6\0\4\7\3\0\1\7"+
    "\23\0\1\7\1\u0129\34\7\6\0\4\7\3\0\1\7"+
    "\23\0\16\7\1\u012a\17\7\6\0\4\7\3\0\1\7"+
    "\23\0\1\7\1\u012b\34\7\6\0\4\7\3\0\1\7"+
    "\23\0\11\7\1\u012c\24\7\6\0\4\7\3\0\1\7"+
    "\23\0\14\7\1\u012d\21\7\6\0\4\7\3\0\1\7"+
    "\23\0\15\7\1\u012e\20\7\6\0\4\7\3\0\1\7"+
    "\23\0\16\7\1\u012f\17\7\6\0\4\7\3\0\1\7"+
    "\23\0\14\7\1\u0130\21\7\6\0\4\7\3\0\1\7"+
    "\23\0\16\7\1\u0131\17\7";

  private static int [] zzUnpackTrans() {
    int [] result = new int[13923];
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
    "\1\0\1\11\10\1\12\11\11\1\1\11\32\1\1\0"+
    "\2\11\1\0\2\1\1\0\1\11\1\0\13\11\56\1"+
    "\3\0\1\1\1\11\63\1\1\11\176\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[305];
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
          case 105: break;
          case 2: 
            { return WHITE_SPACE;
            } 
            // fall through
          case 106: break;
          case 3: 
            { return QUOTIENT;
            } 
            // fall through
          case 107: break;
          case 4: 
            { return MUL;
            } 
            // fall through
          case 108: break;
          case 5: 
            { return NUMBER_CONTENT;
            } 
            // fall through
          case 109: break;
          case 6: 
            { return ID_CONTENT;
            } 
            // fall through
          case 110: break;
          case 7: 
            { return DOT;
            } 
            // fall through
          case 111: break;
          case 8: 
            { return QUOTE;
            } 
            // fall through
          case 112: break;
          case 9: 
            { return BACK;
            } 
            // fall through
          case 113: break;
          case 10: 
            { return LBRACE;
            } 
            // fall through
          case 114: break;
          case 11: 
            { return RBRACE;
            } 
            // fall through
          case 115: break;
          case 12: 
            { return LBRACK;
            } 
            // fall through
          case 116: break;
          case 13: 
            { return RBRACK;
            } 
            // fall through
          case 117: break;
          case 14: 
            { return LPAREN;
            } 
            // fall through
          case 118: break;
          case 15: 
            { return RPAREN;
            } 
            // fall through
          case 119: break;
          case 16: 
            { return COLON;
            } 
            // fall through
          case 120: break;
          case 17: 
            { return SEMI;
            } 
            // fall through
          case 121: break;
          case 18: 
            { return COMMA;
            } 
            // fall through
          case 122: break;
          case 19: 
            { return ASSIGN;
            } 
            // fall through
          case 123: break;
          case 20: 
            { return NOT;
            } 
            // fall through
          case 124: break;
          case 21: 
            { return PLUS;
            } 
            // fall through
          case 125: break;
          case 22: 
            { return MINUS;
            } 
            // fall through
          case 126: break;
          case 23: 
            { return LESS;
            } 
            // fall through
          case 127: break;
          case 24: 
            { return REMAINDER;
            } 
            // fall through
          case 128: break;
          case 25: 
            { return GREATER;
            } 
            // fall through
          case 129: break;
          case 26: 
            { return APOS;
            } 
            // fall through
          case 130: break;
          case 27: 
            { return LINE_COMMENT;
            } 
            // fall through
          case 131: break;
          case 28: 
            { return QUOTIENT_ASSIGN;
            } 
            // fall through
          case 132: break;
          case 29: 
            { return MUL_ASSIGN;
            } 
            // fall through
          case 133: break;
          case 30: 
            { return CONCAT;
            } 
            // fall through
          case 134: break;
          case 31: 
            { return TEXT_CONTENT;
            } 
            // fall through
          case 135: break;
          case 32: 
            { return EQ;
            } 
            // fall through
          case 136: break;
          case 33: 
            { return NOT_EQ;
            } 
            // fall through
          case 137: break;
          case 34: 
            { return PLUS_ASSIGN;
            } 
            // fall through
          case 138: break;
          case 35: 
            { return PLUS_PLUS;
            } 
            // fall through
          case 139: break;
          case 36: 
            { return MINUS_ASSIGN;
            } 
            // fall through
          case 140: break;
          case 37: 
            { return MINUS_MINUS;
            } 
            // fall through
          case 141: break;
          case 38: 
            { return COND_OR;
            } 
            // fall through
          case 142: break;
          case 39: 
            { return COND_AND;
            } 
            // fall through
          case 143: break;
          case 40: 
            { return LESS_OR_EQUAL;
            } 
            // fall through
          case 144: break;
          case 41: 
            { return REMAINDER_ASSIGN;
            } 
            // fall through
          case 145: break;
          case 42: 
            { return GREATER_OR_EQUAL;
            } 
            // fall through
          case 146: break;
          case 43: 
            { return IN;
            } 
            // fall through
          case 147: break;
          case 44: 
            { return IF;
            } 
            // fall through
          case 148: break;
          case 45: 
            { return CO;
            } 
            // fall through
          case 149: break;
          case 46: 
            { return DO;
            } 
            // fall through
          case 150: break;
          case 47: 
            { return TYPE_TAIL;
            } 
            // fall through
          case 151: break;
          case 48: 
            { return GET;
            } 
            // fall through
          case 152: break;
          case 49: 
            { return INT;
            } 
            // fall through
          case 153: break;
          case 50: 
            { return SET;
            } 
            // fall through
          case 154: break;
          case 51: 
            { return VAR;
            } 
            // fall through
          case 155: break;
          case 52: 
            { return ANY;
            } 
            // fall through
          case 156: break;
          case 53: 
            { return NEW;
            } 
            // fall through
          case 157: break;
          case 54: 
            { return MSG;
            } 
            // fall through
          case 158: break;
          case 55: 
            { return MAP;
            } 
            // fall through
          case 159: break;
          case 56: 
            { return CMD;
            } 
            // fall through
          case 160: break;
          case 57: 
            { return FUN;
            } 
            // fall through
          case 161: break;
          case 58: 
            { return FOR;
            } 
            // fall through
          case 162: break;
          case 59: 
            { return BLOCK_COMMENT;
            } 
            // fall through
          case 163: break;
          case 60: 
            { return ELSE;
            } 
            // fall through
          case 164: break;
          case 61: 
            { return ENUM;
            } 
            // fall through
          case 165: break;
          case 62: 
            { return TRUE;
            } 
            // fall through
          case 166: break;
          case 63: 
            { return THIS;
            } 
            // fall through
          case 167: break;
          case 64: 
            { return BIND;
            } 
            // fall through
          case 168: break;
          case 65: 
            { return BOOL;
            } 
            // fall through
          case 169: break;
          case 66: 
            { return LONG;
            } 
            // fall through
          case 170: break;
          case 67: 
            { return CTOR;
            } 
            // fall through
          case 171: break;
          case 68: 
            { return CAST;
            } 
            // fall through
          case 172: break;
          case 69: 
            { return NULL;
            } 
            // fall through
          case 173: break;
          case 70: 
            { return HTTP;
            } 
            // fall through
          case 174: break;
          case 71: 
            { return LIST;
            } 
            // fall through
          case 175: break;
          case 72: 
            { return TCALL;
            } 
            // fall through
          case 176: break;
          case 73: 
            { return THROW;
            } 
            // fall through
          case 177: break;
          case 74: 
            { return USING;
            } 
            // fall through
          case 178: break;
          case 75: 
            { return BREAK;
            } 
            // fall through
          case 179: break;
          case 76: 
            { return CLASS;
            } 
            // fall through
          case 180: break;
          case 77: 
            { return CONST;
            } 
            // fall through
          case 181: break;
          case 78: 
            { return ASYNC;
            } 
            // fall through
          case 182: break;
          case 79: 
            { return AWAIT;
            } 
            // fall through
          case 183: break;
          case 80: 
            { return YIELD;
            } 
            // fall through
          case 184: break;
          case 81: 
            { return WHILE;
            } 
            // fall through
          case 185: break;
          case 82: 
            { return FALSE;
            } 
            // fall through
          case 186: break;
          case 83: 
            { return RETURN;
            } 
            // fall through
          case 187: break;
          case 84: 
            { return ELSEIF;
            } 
            // fall through
          case 188: break;
          case 85: 
            { return STRING;
            } 
            // fall through
          case 189: break;
          case 86: 
            { return STRUCT;
            } 
            // fall through
          case 190: break;
          case 87: 
            { return STATIC;
            } 
            // fall through
          case 191: break;
          case 88: 
            { return PUBLIC;
            } 
            // fall through
          case 192: break;
          case 89: 
            { return ASSERT;
            } 
            // fall through
          case 193: break;
          case 90: 
            { return OPTION;
            } 
            // fall through
          case 194: break;
          case 91: 
            { return DOUBLE;
            } 
            // fall through
          case 195: break;
          case 92: 
            { return NATIVE;
            } 
            // fall through
          case 196: break;
          case 93: 
            { return REFLECT;
            } 
            // fall through
          case 197: break;
          case 94: 
            { return PRIVATE;
            } 
            // fall through
          case 198: break;
          case 95: 
            { return FUNCTOR;
            } 
            // fall through
          case 199: break;
          case 96: 
            { return REGISTER;
            } 
            // fall through
          case 200: break;
          case 97: 
            { return CONTINUE;
            } 
            // fall through
          case 201: break;
          case 98: 
            { return CONSTANT;
            } 
            // fall through
          case 202: break;
          case 99: 
            { return NULLABLE;
            } 
            // fall through
          case 203: break;
          case 100: 
            { return LANGUAGE;
            } 
            // fall through
          case 204: break;
          case 101: 
            { return PROTECTED;
            } 
            // fall through
          case 205: break;
          case 102: 
            { return NAMESPACE;
            } 
            // fall through
          case 206: break;
          case 103: 
            { return HTTPUPLOAD;
            } 
            // fall through
          case 207: break;
          case 104: 
            { return HTTPDOWNLOAD;
            } 
            // fall through
          case 208: break;
          default:
            zzScanError(ZZ_NO_MATCH);
          }
      }
    }
  }


}

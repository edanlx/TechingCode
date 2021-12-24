package exam;

import java.util.HashSet;
import java.util.Set;

public class Code1044 {
    public static void main(String[] args) {
        // ana
        System.out.println(new Code1044().longestDupSubstring("banana"));
        // ""
        System.out.println(new Code1044().longestDupSubstring("abcd"));
        // as
        System.out.println(new Code1044().longestDupSubstring("qoassasmcmntmgylwkujr"));
        // a
        System.out.println(new Code1044().longestDupSubstring("aa"));
        // maydg
        System.out.println(new Code1044().longestDupSubstring("hnqibjoiyevxgwigqgvpwwwpmaydgmmwlrlzsfrspoocbbyiafezpmxpkymlbgdqsggjjbyfajvthanhqvtkdqujdqluiztivfxqstmkubeeltmcuuzjmmjnqsgiwbpncvewxjglrwztntnajwaujdjilhivfwfgfmkjzbzoiptyqltbpwpemtqpucjdeoqsuffcptpqqobmciqcrzkvobxkxoinjxgaldxvqumexoojsiwsrdzlgpqvbvehumuyqjlwpzxobrkoqajehnrxaaoyvnxgbcstrmyyhbefxhgkjlbumnvkpaqtyxgiintrzieoywjuecrpeisgcoetlcynbdivzjzjoqzwnjsatymztkrtuapnrympunyjfuxhanddebepsakubsokaddycmeytsdfdtggsotbkckorgttrdaxsczbwvjdknmevikqqraoswfgvjofwdumudjvojmtzovngxwtoovdqxqdmafxwvhgyxznsjakphynpqudzvdhiiyowgttjslpdhaaptybqdpuqrwqajtdzslqpzjkrmpjrpuglroaozuccwcbfsxhzbaljbmosmychtsswzxoxoiwocxblysenxnfcthaecwyznuuujwhvxhapfoasjkmyowltskpzmvbvhtjlmddyqiygminzgujtesrlbvhcwetlvfouugyfpajxafcsqvrhtlybjngbbkzewzwnyldyarewyjffmibppflsjcgqzzsjppxluzgzxfsinxovkujftjfgffadqnayqzqcdnloyrorykoyweslgvhvdtajrymubqampvkberchlfzlexgvghfxifejvxknvzcmkjbjuwbsrfzhbpdbiuzvrfkrqneqhhliihvfyttmaydgunzxrxluwmsugxtgzuipgkkwsbqwtwyhiojmhkpsdonjudjsfwcyqpoeifyzfluwswmnhstovjqwowuhokwmtpcoupyzdnpllxklwlhuayzpbvkoxzijjithrlpwgsgxsdtrcohxymwavhxrwxbdhfgosqjnfwfkezkdmptfqrxybiejpyzhvzbmgbmjgzburbbamegrjcidotpnetygyqtbbqkdasjdfgrpgygnfhckuwsoqqvseqyuvbqfdegqtlldtapciiijbiqtyeiulqaquszwqjuokplcjgviigdhrcprvukyfgbtcdvqptftsefxojicuwsksstasirisiloounfwnuqzvrnymmpinonnndzxdhyclfecpvsiarbnbxavedymcfajwsqhibbuxxpuirvzuxxnacqghmgzgtvtctlsqkxubrpyclmfplxuzieckoghimqmvmrnwkffnjbhmkttdlzfeckvwcwgoxboggfjlecmsnvkoqantlimlupauxezujtphgtoqvknkmdhjrkoyseqqpiuozgasqivuqwomghuwlyfnsckcsvdnoyslchpidzkfnmxudgurjrfiytnidmqutwqnfcsxjyrrkhrfcbtoehbtinptkmxzexatjyhwlszkrdalyznavhvteyejfxxgisvpdngsvejybgcwkbqahptpnnjbxnprpxngbjfiokuyigexbgwfwtpxtljutgskixqofodjpihralltszsvduuvpwnpkkbneckergrexlykqbyogycmroyvjeiupxnsjujavgvpkthwofdboddbdbcynvrmwvdzoujemsvqyuzeaihytirkohzirbfeidsiyafxpnmhntpfkcnqacqmuwfexmgwnnghvthwiftsheergwepkncktqykuqpwkzhqicavrrqbtiloqrcnqlhjigcffnqblspdhyblgrpwylocwxcqrklfxxjbsmqngbgannpjhhagltixpdiwiaupvdhmroixmfasvriadayjznmxqyhqnnusitudoitppzupyyicvtopprlrxmqlanlqdzimkfyrxczohvlpnsvaajmeukypqihghrjsduoadyhiwzwuamdxofanavouwgahmncewstdyofpxwgmqqozstwihpaogxchaucuqczjfqbkdrrbvzvlrlxbbcjitwqcetwcbwqrgxpfwdztkeewcchrxpazcwxrdnlcukccqdxvugzawfsboooawczmuuzffkpxfzomiwzzwjtmflnvflwcgrogzzjhozvqfrmknzgrirymgjfplxqsvulvxxqcmaefklwlpqbletpdhxtizltqnjfxvpcwobmnscyikientysbepfgpnrplgattcqdkljipfdcyfnjdkkpablrudxoitxpjigdswpvbbhuosytugnuhxhebttzjogemlcaxdjpfzhakzymfrrzuzhuifrvoumzfoeeqzqsiqxcqyjnpnmxcvwudmddutaistrhdfiqtwfhteyxtfvivexfviuewnsyafnuuxojsxygqcxlubrckzqjqlymffiomdirzweoyxqqefrrsgmysgfrwtwaiktivklmqwlixzhstenipiehhzlpuwnoyazyobufneycgejdbjitchkqcdfcbgxxdiwletzojljiailmymvazuouecshzqovndrmgzxjdfdgnhecnwbgzucyyufqxwzdehgxjklscetxswtdhskenetpgmsvycarjbbvjbjnotmmpicguzsnkfaufrvqoplsifxcxsmewesxsnjricrjrwhrvrnpnxfrrslllzwuqkobzdnqbypjnroupzgkmoxvroxhvzicripqeeahrnkkeydzjzxbqqjbalvrlwsktpdlxuhomuozdbbxurnghrcwfadqltaiflkaynimanjwfgfwrbqnqzizjqxlqgysowtvfmyniwncjywjjrnpgueexefqmpzoljiuneexixnwuvuoxnzqwghqdnzhreaqkkgnwdnfdaloxetdqwrkodpitcjrhndowvyjmapwrmgepqjktkzcrtibkgtzotnrxzfjohxrwolmtswezchpzqdbssvxqkxkbhyeoqnvhsnfmhfalsbsodlzdqxvnyeawvqmsuxbfkeohisohipcrpxipdyzukunecgsdvdelkamxbutjmhorxnashhqvhygppkhmmuiimqaxdmuakdtqmrldfodzcgbhjhxdxlhwlpsimudbzpsybhdztpzbwnleuyrhbhrficuygiktgxghafbizowiakggktxytetfjgcwkkhgtpfpotgloaiyscevggrtfsqyqouyljvmybevgmgrtzfhivxfordeofxhyimecslbngucvpoxxcpeoxfajavizfdiylfhzhymdtoxnipvlgnodbytnlkoudqyligxtivpvqhyxgvofhiuaesgxloeispopbjukbfppquinpswmnifizkqcwqopifbsjtysdkbzmlnwpooanzvbkdvurjfcagdimomqynatgqfqefqwqeqhxvtvdivqspxxhgupbfuwykziochcixrisejgvplsrjxzfnzswghotvgsonoftfgqjlsssysgxecccypbwdtlglzgeehbigrvizxrluruwxujfjsobhdntmlmdsrjmpeuifzvjsrkwtnlssjdynjajtgatejmpuuqxvrhsiyjledxyhluuzsevuicoegkqtcwjkrnruxyondhcmusjucanmyjpvkvuoolcujawtfkdwarwkysgprlumqtvkqowxbhprpbuzjahwwblmlcakhcdjfuywcmhncrnfxfmgkoumyaehwotwrmxxtjcjzjaexnrnlohyfwwqyoizkdzfcmuggrzjnczamjiapxwqvcglhqvowihpdujlkuasjwlnjhdiotqlwaawikughzexoxfdpamdqxvjubihvqorutuhugibnbavmjoneukkpijtgeyhdcpwbsbysjewgigmawxqbrroagbzeehutzgszq"));

    }

    public String longestDupSubstring3(String s) {
        String maxStr = "";
        for (int i = 0, j = 1; i < s.length() - j; i++) {
            String substring = s.substring(i, i + j);
            if (s.indexOf(substring, i + 1) > -1) {
                maxStr = substring;
                j += 1;
                i -= 1;
            }
        }
        return maxStr;
    }

    public String longestDupSubstring(String s) {
        int l = 0;
        int r = s.length() - 1;
        int start = 0;
        int length = 0;
        String result = "";
        while (l < r) {
            // 需要检验的长度
            int m = l + (r - l + 1) / 2;
            String tempResult = myCheck(s, m, start);
            if (tempResult.length() != 0) {
                // 有值更新length
                length = m;
                // 有值更新l，让检验长度增加
                l = m;
                result = tempResult;
            } else {
                // 无值
                r = m - 1;
            }
        }
        if (length == 0) {
            return "";
        }
        return result;
    }

    public String myCheck(String s, int length, int start) {
        Set<String> check = new HashSet<>();
        for (int i = start; i <= s.length() - length; i++) {
            String substring = s.substring(i, i + length);
            if (!check.add(substring)) {
                return substring;
            }
        }
        return "";
    }


    long[] h, p;

    /**
     * 因为大的匹配字符串必然包含小的
     * 所以先按照1/2长度依次匹配，如果匹配不到则在减小1/2，如果能匹配到则增大1/2
     * 同时利用滑动哈希的思想，判断是否有匹配过Rabin-Karp
     *
     * @param s
     * @return
     */
    public String longestDupSubstring2(String s) {
        int P = 1313131, n = s.length();
        h = new long[n + 10];
        p = new long[n + 10];
        p[0] = 1;
        for (int i = 0; i < n; i++) {
            p[i + 1] = p[i] * P;
            h[i + 1] = h[i] * P + s.charAt(i);
        }
        String ans = "";
        int l = 0, r = n;
        while (l < r) {
            int mid = l + r + 1 >> 1;
            String t = check(s, mid);
            if (t.length() != 0) {
                l = mid;
            } else {
                r = mid - 1;
            }
            ans = t.length() > ans.length() ? t : ans;
        }
        return ans;
    }

    String check(String s, int len) {
        int n = s.length();
        Set<Long> set = new HashSet<>();
        for (int i = 1; i + len - 1 <= n; i++) {
            int j = i + len - 1;
            long cur = h[j] - h[i - 1] * p[j - i + 1];
            if (set.contains(cur)) {
                return s.substring(i - 1, j);
            }
            set.add(cur);
        }
        return "";
    }
}

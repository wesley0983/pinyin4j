package com.example.pinyin4j;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

public class PinyinUtil {

    private static final String DEFAULT_GROUP_NAME = "#";
    // pinyin4j格式类
    private static HanyuPinyinOutputFormat format = null;
    // 拼音字符串数组
    private static String[] pinyin;
    /**
     * 将字符串转换成拼音数组
     *
     * @param src
     * @return
     */
    public static String[] stringToPinyin(String src) {
        return stringToPinyin(src, false, null);
    }
    /**
     * 将字符串转换成拼音数组
     *
     * @param src
     * @return
     */
    public static String[] stringToPinyin(String src, String separator) {
        return stringToPinyin(src, true, separator);
    }

    /**
     * 将字符串转换成拼音数组
     *
     * @param src
     * @param isPolyphone
     *            是否查出多音字的所有拼音
     * @param separator
     *            多音字拼音之间的分隔符
     * @return
     */
    public static String[] stringToPinyin(String src, boolean isPolyphone,
                                          String separator) {
        // 判断字符串是否为空
        if ("".equals(src) || null == src) {
            return null;
        }
        char[] srcChar = src.toCharArray();
        int srcCount = srcChar.length;
        String[] srcStr = new String[srcCount];

        for (int i = 0; i < srcCount; i++) {
            srcStr[i] = charToPinyin(srcChar[i], isPolyphone, separator);
        }
        return srcStr;
    }

    /**
     * 将单个字符转换成拼音
     *
     * @param src
     * @return
     */
    public static String charToPinyin(char src, boolean isPolyphone,
                                      String separator) {
        // 创建汉语拼音处理类
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 输出设置，大小写，音标方式
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        StringBuffer tempPinying = new StringBuffer();


        // 如果是中文
        if (src > 128) {
            try {
                // 转换得出结果
                String[] strs = PinyinHelper.toHanyuPinyinStringArray(src,
                        defaultFormat);


                // 是否查出多音字，默认是查出多音字的第一个字符
                if (isPolyphone && null != separator) {
                    for (int i = 0; i < strs.length; i++) {
                        tempPinying.append(strs[i]);
                        if (strs.length != (i + 1)) {
                            // 多音字之间用特殊符号间隔起来
                            tempPinying.append(separator);
                        }
                    }
                } else {
                    tempPinying.append(strs[0]);
                }

            } catch (BadHanyuPinyinOutputFormatCombination e) {
                e.printStackTrace();
            }
        } else {
            tempPinying.append(src);
        }

        return tempPinying.toString();

    }


    public static String hanziToPinyin(String hanzi){
        return hanziToPinyin(hanzi," ");
    }
    /**
     * 将汉字转换成拼音
     * @param hanzi
     * @param separator
     * @return
     */
    @SuppressWarnings("deprecation")
    public static String hanziToPinyin(String hanzi, String separator){
        // 创建汉语拼音处理类
        HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
        // 输出设置，大小写，音标方式
        defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
        defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

        String pinyingStr="";
        try {
            pinyingStr=PinyinHelper.toHanyuPinyinString(hanzi, defaultFormat, separator);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return pinyingStr;
    }
    /**
     * 将字符串数组转换成字符串
     * @param str
     * @param separator 各个字符串之间的分隔符
     * @return
     */
    public static String stringArrayToString(String[] str, String separator) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < str.length; i++) {
            sb.append(str[i]);
            if (str.length != (i + 1)) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }
    /**
     * 简单的将各个字符数组之间连接起来
     * @param str
     * @return
     */
    public  static String stringArrayToString(String[] str){
        return stringArrayToString(str,"");
    }
    /**
     * 将字符数组转换成字符串
     * @param str
     * @param separator 各个字符串之间的分隔符
     * @return
     */
    public static String charArrayToString(char[] ch, String separator) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < ch.length; i++) {
            sb.append(ch[i]);
            if (ch.length != (i + 1)) {
                sb.append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * 将字符数组转换成字符串
     * @param str
     * @return
     */
    public static String charArrayToString(char[] ch) {
        return charArrayToString(ch," ");
    }

    /**
     * 取汉字的首字母
     * @param src
     * @param isCapital 是否是大写
     * @return
     */
    public static char[]  getHeadByChar(char src,boolean isCapital){
        //如果不是汉字直接返回
        if (src <= 128) {
            return new char[]{src};
        }
        //获取所有的拼音
        String[]pinyingStr=PinyinHelper.toHanyuPinyinStringArray(src);

        //创建返回对象
        int polyphoneSize=pinyingStr.length;
        char [] headChars=new char[polyphoneSize];
        int i=0;
        //截取首字符
        for(String s:pinyingStr){
            char headChar=s.charAt(0);
            //首字母是否大写，默认是小写
            if(isCapital){
                headChars[i]= Character.toUpperCase(headChar);
            }else{
                headChars[i]=headChar;
            }
            i++;
        }

        return headChars;
    }
    /**
     * 取汉字的首字母(默认是大写)
     * @param src
     * @return
     */
    public static char[]  getHeadByChar(char src){
        return getHeadByChar(src,true);
    }
    /**
     * 查找字符串首字母
     * @param src
     * @return
     */
    public  static String[] getHeadByString(String src){
        return getHeadByString( src, true);
    }
    /**
     * 查找字符串首字母
     * @param src
     * @param isCapital 是否大写
     * @return
     */
    public  static String[] getHeadByString(String src, boolean isCapital){
        return getHeadByString( src, isCapital,null);
    }
    /**
     * 查找字符串首字母
     * @param src
     * @param isCapital 是否大写
     * @param separator 分隔符
     * @return
     */
    public  static String[] getHeadByString(String src, boolean isCapital, String separator){
        char[]chars=src.toCharArray();
        String[] headString=new String[chars.length];
        int i=0;
        for(char ch:chars){

            char[]chs=getHeadByChar(ch,isCapital);
            StringBuffer sb=new StringBuffer();
            if(null!=separator){
                int j=1;

                for(char ch1:chs){
                    sb.append(ch1);
                    if(j!=chs.length){
                        sb.append(separator);
                    }
                    j++;
                }
            }else{
                sb.append(chs[0]);
            }
            headString[i]=sb.toString();
            i++;
        }
        return headString;
    }

    /**
     * 获取字符串对应的拼音字符串，分两个步骤
     * 一、先对每个字符进行处理，规则：
     * 1、中文字符，返回对应的大写的拼音
     * 2、字母，转换成大写字母
     * 3、其他，不作处理
     * 二、判断字符串的首个字符是否是字母或汉字，如果是，不做处理，否则在第一步转换后的结果前面添加
     * 一个Unicode值比字母大的任意一个字符（保证compare排序的时候位置在字母后面）
     *
     * @param str
     * @return
     */
    public static String getPinyin(String str) {
        StringBuilder resultSb = new StringBuilder();
        for (char item : str.trim().toCharArray()) {
            String itemStr = Character.toString(item);
            if (isLetter(itemStr)) {
                resultSb.append(Character.toString(item).toUpperCase());
            } else {
                resultSb.append(Character.toString(item));
            }
        }

        if (DEFAULT_GROUP_NAME.equals(getLetter(str))) {
            resultSb.insert(0, "~");
        }
        return resultSb.toString();
    }

    /**
     * 判断是否是中文
     *
     * @param str
     * @return
     */
    private static boolean isChinese(String str) {
        String regex = "^[\u4e00-\u9fa5]+$";
        return str.matches(regex);
    }

    /**
     * 判断是否是字母
     *
     * @param str
     * @return
     */
    private static boolean isLetter(String str) {
        String regex = "^[a-zA-Z]+$";
        return str.matches(regex);
    }

    /**
     * 获取字符串对应的groupName
     * 规则：
     * 1、字母直接转换成大写字母
     * 2、中文转换成拼音并返回拼音首个字符对应的大写字母
     * 3、其他返回#
     *
     * @param name
     * @return
     */
    private static String getLetter(String name) {
        String result = DEFAULT_GROUP_NAME;
        String firstNameLetter = String.valueOf(name.charAt(0));
        if (isLetter(firstNameLetter)) {
            result = firstNameLetter.toUpperCase();
        } else if (isChinese(firstNameLetter)) {
            result = String.valueOf(getCharPinYin(firstNameLetter.charAt(0)).charAt(0))
                    .toUpperCase();
        }

        return result;
    }

    private static String getCharPinYin(char pinYinStr) {

        try {
            // 执行转换
            pinyin = PinyinHelper.toHanyuPinyinStringArray(pinYinStr, format);
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        // pinyin4j规则，当转换的符串不是汉字，就返回null
        if (pinyin == null) {
            return null;
        }

        // 多音字会返回一个多音字拼音的数组，pinyiin4j并不能有效判断该字的读音
        return pinyin[0];
    }

    public static void main(String[] args) {
        System.out.println(PinyinUtil.stringArrayToString(getHeadByString("我的心肝爱上"),"-"));

    }

}


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
 
/**
 * @author hbli 2018年3月12日 下午3:28:39
 * 
 */
public class IdCardGenerator {
    // 18位身份证号码各位的含义:  
    // 1-2位省、自治区、直辖市代码；  
    // 3-4位地级市、盟、自治州代码；  
    // 5-6位县、县级市、区代码；  
    // 7-14位出生年月日，比如19670401代表1967年4月1日；  
    // 15-17位为顺序号，其中17位（倒数第二位）男为单数，女为双数；  
    // 18位为校验码，0-9和X。  
    // 作为尾号的校验码，是由把前十七位数字带入统一的公式计算出来的，  
    // 计算的结果是0-10，如果某人的尾号是0－9，都不会出现X，但如果尾号是10，那么就得用X来代替，  
    // 因为如果用10做尾号，那么此人的身份证就变成了19位。X是罗马数字的10，用X来代替10  
    public static void main(String[] args) {  
    	for(int i=0;i<10;i++) {
    		IdCardGenerator cre = new IdCardGenerator();  
            String randomID = cre.getRandomID();  
            String name = cre.getName();
            System.out.println(name + "		" + randomID);  
    	}
    	
    }  
    public String getName() {
        Random random = new Random();
        String[] Surname = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王", "冯", "陈", "褚", "卫", "蒋", "沈", "韩", "杨", "朱", "秦", "尤", "许",
                "何", "吕", "施", "张", "孔", "曹", "严", "华", "金", "魏", "陶", "姜", "戚", "谢", "邹", "喻", "柏", "水", "窦", "章", "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
                "鲁", "韦", "昌", "马", "苗", "凤", "花", "方", "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐", "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤", "滕", "殷",
                "罗", "毕", "郝", "邬", "安", "常", "乐", "于", "时", "傅", "皮", "卞", "齐", "康", "伍", "余", "元", "卜", "顾", "孟", "平", "黄", "和",
                "穆", "萧", "尹", "姚", "邵", "湛", "汪", "祁", "毛", "禹", "狄", "米", "贝", "明", "臧", "计", "伏", "成", "戴", "谈", "宋", "茅", "庞", "熊", "纪", "舒",
                "屈", "项", "祝", "董", "梁", "杜", "阮", "蓝", "闵", "席", "季"};
        String girl = "秀娟英华慧巧美娜静淑惠珠翠雅芝玉萍红娥玲芬芳燕彩春菊兰凤洁梅琳素云莲真环雪荣爱妹霞香月莺媛艳瑞凡佳嘉琼勤珍贞莉桂娣叶璧璐娅琦晶妍茜秋珊莎锦黛青倩婷姣婉娴瑾颖露瑶怡婵雁蓓纨仪荷丹蓉眉君琴蕊薇菁梦岚苑婕馨瑗琰韵融园艺咏卿聪澜纯毓悦昭冰爽琬茗羽希宁欣飘育滢馥筠柔竹霭凝晓欢霄枫芸菲寒伊亚宜可姬舒影荔枝思丽 ";
        String boy = "伟刚勇毅俊峰强军平保东文辉力明永健世广志义兴良海山仁波宁贵福生龙元全国胜学祥才发武新利清飞彬富顺信子杰涛昌成康星光天达安岩中茂进林有坚和彪博诚先敬震振壮会思群豪心邦承乐绍功松善厚庆磊民友裕河哲江超浩亮政谦亨奇固之轮翰朗伯宏言若鸣朋斌梁栋维启克伦翔旭鹏泽晨辰士以建家致树炎德行时泰盛雄琛钧冠策腾楠榕风航弘";
        int index = random.nextInt(Surname.length - 1);
        String name = Surname[index]; //获得一个随机的姓氏
        int i = random.nextInt(3);//可以根据这个数设置产生的男女比例
        if(i==2){
            int j = random.nextInt(girl.length()-2);
            if (j % 2 == 0) {
                name =  name + girl.substring(j, j + 2);
            } else {
                name = name + girl.substring(j, j + 1);
            }

        }
        else{
            int j = random.nextInt(girl.length()-2);
            if (j % 2 == 0) {
                name = name + boy.substring(j, j + 2);
            } else {
                name =  name + boy.substring(j, j + 1);
            }

        }

        return name;
    }
  
    /** 
     * 获取随机生成的身份证号码 
     *  
     * @author mingzijian 
     * @return 
     */  
    public String getRandomID() {  
        String id = "420222199204179999";  
        // 随机生成省、自治区、直辖市代码 1-2  
        String provinces[] = { "11", "12", "13", "14", "15", "21", "22", "23",  
                "31", "32", "33", "34", "35", "36", "37", "41", "42", "43",  
                "44", "45", "46", "50", "51", "52", "53", "54", "61", "62",  
                "63", "64", "65", "71", "81", "82" };  
        String province = randomOne(provinces);  
        // 随机生成地级市、盟、自治州代码 3-4  
        String city = randomCityCode(18);  
        // 随机生成县、县级市、区代码 5-6  
        String county = randomCityCode(28);  
        // 随机生成出生年月 7-14  
        String birth = randomBirth(20, 50);  
        // 随机生成顺序号 15-17(随机性别)  
        String no = new Random().nextInt(899) + 100+"";    
        // 随机生成校验码 18  
        String checks[] = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9",  
                "X" };  
        String check = randomOne(checks);  
        // 拼接身份证号码  
        id = province + city + county + birth + no + check;  
        return id;  
    }  
  
    /** 
     * 从String[] 数组中随机取出其中一个String字符串 
     *  
     * @author mingzijian 
     * @param s 
     * @return 
     */  
    public String randomOne(String s[]) {  
        return s[new Random().nextInt(s.length - 1)];  
    }  
  
    /** 
     * 随机生成两位数的字符串（01-max）,不足两位的前面补0 
     *  
     * @author mingzijian 
     * @param max 
     * @return 
     */  
    public String randomCityCode(int max) {  
        int i = new Random().nextInt(max) + 1;  
        return i > 9 ? i + "" : "0" + i;  
    }  
  
    /** 
     * 随机生成minAge到maxAge年龄段的人的生日日期 
     *  
     * @author mingzijian 
     * @param minAge 
     * @param maxAge 
     * @return 
     */  
    public String randomBirth(int minAge, int maxAge) {  
        SimpleDateFormat dft = new SimpleDateFormat("yyyyMMdd");// 设置日期格式  
        Calendar date = Calendar.getInstance();  
        date.setTime(new Date());// 设置当前日期  
        // 随机设置日期为前maxAge年到前minAge年的任意一天  
        int randomDay = 365 * minAge  
                + new Random().nextInt(365 * (maxAge - minAge));  
        date.set(Calendar.DATE, date.get(Calendar.DATE) - randomDay);  
        return dft.format(date.getTime());  
    }  
}
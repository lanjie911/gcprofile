package cn.bj.brook.algorithm.threepg;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class BlockUtil {
    private String[] colors = new String[]{"red","blue","yellow","black","green"};
    private String[] sizes = new String[]{"L","M","XL","XXL","XXXL","S"};
    private String[] shapes = new String[]{"circle","triangle","rectangle"};

    public String randomColor(){
        int m = (int)((Math.random()*10)%5);
        return colors[m];
    }

    public String randomSize(){
        int m = (int)((Math.random()*10)%6);
        return sizes[m];
    }

    public String randomShape(){
        int m = (int)((Math.random()*10)%3);
        return shapes[m];
    }

    public Integer randomId(){
        return Math.abs(new Random().nextInt());
    }

    public List<Block> generateFromFile(){
        List<Block> blocks = new LinkedList<>();
        try {

            InputStream is = BlockUtil.class.getResourceAsStream("/data.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while((line=br.readLine())!=null){
                if(line.indexOf("分组")!=-1){
                    continue;
                }
                String[] datas = line.split(",");
                Block b = new Block();
                b.id = Integer.parseInt(datas[0].split("=")[1]);
                b.color = datas[1].split("=")[1];
                b.size = datas[2].split("=")[1];
                b.shape = datas[3].split("=")[1];
                blocks.add(b);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return blocks;
    }

    public static void main(String[] args) {
        try {

            InputStream is = BlockUtil.class.getResourceAsStream("/data.txt");
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while((line=br.readLine())!=null){
                System.out.println(line);
            }
            br.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

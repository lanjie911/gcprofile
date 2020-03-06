package cn.bj.brook.algorithm.threepg;

import org.w3c.dom.Node;

import java.util.*;

/**
 * 三种属性问题
 * 颜色、形状、大小，分n堆，尽可能均匀分布
 * 这个问题的本质是负载均衡算法
 * 根据CPU、内存和带宽分配资源
 */
public class ThreePropBlock {

    BlockUtil util = null;

    ThreePropBlock() {
        util = new BlockUtil();
    }

    public List<Block> generateBlocks() {
        return util.generateFromFile();
    }

    public List<Block> generateBlocks(int num) {
        List<Block> list = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            Block b = new Block();
            b.color = util.randomColor();
            b.size = util.randomSize();
            b.shape = util.randomShape();
            b.id = util.randomId();
            list.add(b);
        }
        return list;
    }


    public static void group1(List<Block> list, BlockGroup[] groups){
        long beforeTime = System.currentTimeMillis();
        for(Block b: list){
            BlockGroup group = GroupSelectUtil.selectGroup(b,groups);
            group.addBlock(b);
        }
        long endTime = System.currentTimeMillis();
        printExeInfo(groups,endTime-beforeTime);
    }

    public static void printExeInfo(BlockGroup[] groups,long execTime){
        String sep = "";
        for(BlockGroup g:groups){
            System.out.println("-----------分组开始----------");
            List<Block> bs = g.members;
            for(Block b:bs){
                System.out.println(b);
            }
            System.out.println("-----------分组结束----------");
            sep += g.total+",";
        }
        System.out.println("分组大小:"+sep+"执行时间："+execTime+"毫秒");
    }

    // 填充法
    // 现排序
    // 后轮询
    public static void group2(List<Block> list, BlockGroup[] groups){

    }

    public static void main(String[] args) {
        ThreePropBlock app = new ThreePropBlock();
        // 生成积木块 1000 个
        //List<Block> list = app.generateBlocks(50);

        /*for(Block b : list){
           System.out.println(b);
        }
        return;*/

        boolean generateFromFile = false;
        // 几个积木
        int blockNum  =12;
        // 分n组
        int groupNum = 3;

        List<Block> list = generateFromFile?app.generateBlocks():app.generateBlocks(blockNum);

        // 思路，按照属性最多的分，这个可以通过循环积木块，复杂度o(n)
        // 计算出来，我们已经知道了属性分类最多的是shape


        BlockGroup[] groups = new BlockGroup[groupNum];
        for(int i=0;i<groupNum;i++){
            groups[i]=new BlockGroup();
        }

        group1(list,groups);
    }


}

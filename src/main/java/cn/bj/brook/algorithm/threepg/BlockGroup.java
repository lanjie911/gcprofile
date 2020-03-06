package cn.bj.brook.algorithm.threepg;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BlockGroup {
    // 颜色计数器
    public Map<String, Integer> colorSum;
    // 大小计数器
    public Map<String, Integer> sizeSum;
    // 形状计数器
    public Map<String, Integer> shapeSum;
    // 总计数器
    public int total=0;

    public List<Block> members;

    public BlockGroup(){
        colorSum = new HashMap<>();
        sizeSum = new HashMap<>();
        shapeSum = new HashMap<>();
        members = new LinkedList<>();
    }

    // 组内分配一个积木
    public void addBlock(Block block){
        members.add(block);
        String color = block.color;
        if(colorSum.get(color) == null){
            colorSum.put(color,1);
        }else{
            Integer tmp = colorSum.get(color);
            colorSum.put(color,tmp+1);
        }

        String size = block.size;
        if(sizeSum.get(size) == null){
            sizeSum.put(size,1);
        }else{
            Integer tmp = sizeSum.get(size);
            sizeSum.put(size,tmp+1);
        }

        String shape = block.shape;
        if(shapeSum.get(shape) == null){
            shapeSum.put(shape,1);
        }else{
            Integer tmp = shapeSum.get(shape);
            shapeSum.put(shape,tmp+1);
        }
        total = members.size();
    }
}

package cn.bj.brook.algorithm.threepg;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

public class GroupSelectUtil {

    private static BlockGroup select0Group(Block block, BlockGroup[] groups){
        for(BlockGroup group:groups){
            if(group.total == 0){
                return group;
            }
        }
        return null;
    }

    private static BlockGroup select0ColorGroup(Block block, BlockGroup[] groups){
        int min = -1;
        BlockGroup tmpGroup = null;
        for(BlockGroup group:groups){
            if(group.colorSum.get(block.color) ==  null){
                return group;
            }else{
                int currentSum = group.colorSum.get(block.color);
                if(min == -1){
                    min = currentSum;
                    tmpGroup = group;
                }else{
                    if(currentSum <= min){
                        min = currentSum;
                        tmpGroup = group;
                    }
                }
                return tmpGroup;
            }
        }
        return null;
    }

    private static BlockGroup select0SizeGroup(Block block, BlockGroup[] groups){
        int min = -1;
        BlockGroup tmpGroup = null;
        for(BlockGroup group:groups){
            if(group.sizeSum.get(block.size) ==  null){
                return group;
            }else{
                int currentSum = group.sizeSum.get(block.size);
                if(min == -1){
                    min = currentSum;
                    tmpGroup = group;
                }else{
                    if(currentSum <= min){
                        min = currentSum;
                        tmpGroup = group;
                    }
                }
                return tmpGroup;
            }
        }
        return null;
    }

    private static BlockGroup select0ShapeGroup(Block block, BlockGroup[] groups){
        int min = -1;
        BlockGroup tmpGroup = null;
        for(BlockGroup group:groups){
            if(group.shapeSum.get(block.shape) ==  null){
                return group;
            }else{
                int currentSum = group.shapeSum.get(block.shape);
                if(min == -1){
                    min = currentSum;
                    tmpGroup = group;
                }else{
                    if(currentSum <= min){
                        min = currentSum;
                        tmpGroup = group;
                    }
                }
                return tmpGroup;
            }
        }
        return null;
    }

    private static BlockGroup colorFirstSelector(Block block, BlockGroup[] groups){
        // 策略优先级2，选择颜色分布为0的
        BlockGroup groupByColor = select0ColorGroup(block,groups);


        // 策略优先级3，选择形状分布为0的
        BlockGroup groupByShape = select0ShapeGroup(block,groups);


        // 策略优先级4，选择大小分布为0的
        BlockGroup groupBySize = select0SizeGroup(block,groups);

        int num1 = groupByColor.colorSum.get(block.color) == null?0:groupByColor.colorSum.get(block.color);
        int num2 = groupByShape.shapeSum.get(block.shape) == null?0:groupByShape.shapeSum.get(block.shape);
        int num3 = groupBySize.sizeSum.get(block.size)==null?0:groupBySize.sizeSum.get(block.size);

        if(num1 <= num2 && num1 <= num3){
            return groupByColor;
        }

        if(num2 <= num1 && num2 <= num3){
            return groupByShape;
        }

        if(num3 <= num2 && num3 <= num1){
            return groupBySize;
        }

        return null;
    }

    public static boolean isMatch2Property(Block block, Block ele){
        if(block.color.equalsIgnoreCase(ele.color) && block.size.equalsIgnoreCase(ele.size)){
            return true;
        }
        if(block.size.equalsIgnoreCase(ele.size) && block.shape.equalsIgnoreCase(ele.shape)){
            return true;
        }
        if(block.color.equalsIgnoreCase(ele.color) && block.shape.equalsIgnoreCase(ele.shape)){
            return true;
        }
        return false;
    }


    // 交叉优先
    // 和任意组的匹配元素越少越好
    public  static  BlockGroup crossWeightPro(Block block, BlockGroup[] groups){
        int prevMatch = -1;
        int thisMatch = 0;
        BlockGroup tmpGroup = null;
        for(BlockGroup group: groups){
            List<Block> blocks = group.members;
            for(Block ele:blocks){
                boolean isMatch2 = isMatch2Property(block,ele);
                if(isMatch2){
                    thisMatch += 1;
                }
            }
            if(prevMatch == -1){
                prevMatch = thisMatch;
                tmpGroup = group;
            }else{
                if(thisMatch == 0){
                    return group;
                }
                if(thisMatch < prevMatch){
                    prevMatch = thisMatch;
                    tmpGroup = group;
                }
            }
            thisMatch = 0;
        }
        return tmpGroup;
    }

    // 最大距离法
    public  static  BlockGroup maxVar(Block block, BlockGroup[] groups){

        int maxDistance = -1;
        BlockGroup tmp_group = null;

        for(BlockGroup group: groups){
            Integer disColor = group.colorSum.get(block.color);
            Integer disSize = group.sizeSum.get(block.size);
            Integer disShape = group.shapeSum.get(block.shape);

            int dis_color = disColor == null?0:disColor.intValue();
            int dis_size = disSize == null?0:disSize.intValue();
            int dis_shape = disShape == null?0:disShape.intValue();

            int t = dis_color+dis_size+dis_shape;

            if(maxDistance == -1){
                maxDistance = t;
                tmp_group = group;
            }else{
                if(t == 0){
                    return group;
                }
                if(t < maxDistance){
                    maxDistance = t;
                    tmp_group = group;
                }
            }
        }

        return tmp_group;

    }

    public static BlockGroup selectGroup(Block block, BlockGroup[] groups){

        // 策略优先级1， 首先选节点为空的
        BlockGroup group = select0Group(block,groups);
        if(group != null){
            return group;
        }

        // 颜色优先策略
        // return colorFirstSelector(block,groups);
        // 匹配权重策略，优先选择少于2项匹配
        // 未来可优化为2项少于，再对比一项
        // return crossWeightPro(block,groups);

        // 距离最大策略
        return maxVar(block,groups);
    }
}

package cn.bj.brook.algorithm.common;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.LinkedList;
import java.util.List;

public class JavaScriptDataConverter {
    public static ListNode convert(String args) {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("javascript");
        try {
            engine.eval("var paraArray = "+args);
            List<ListNode> list = new LinkedList<>();
            engine.put("container", list);
            engine.eval("" +
                    "for(var i=0;i<paraArray.length;i++){" +
                    "    var innerArray = paraArray[i];" +
                    "    for(var j=0;j<innerArray.length;j++){" +
                    "        var node = new cn.bj.brook.algorithm.common.ListNode();" +
                    "        print(innerArray[j]);" +
                    "    }" +
                    "}");

        } catch (ScriptException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        JavaScriptDataConverter.convert("[[1,4,5],[1,3,4],[2,6]]");
    }
}

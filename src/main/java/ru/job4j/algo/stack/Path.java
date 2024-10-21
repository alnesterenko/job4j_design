package ru.job4j.algo.stack;

import java.util.Stack;

class Path {
    public String simplify(String path) {
        var stack = new Stack<String>();
        var components = path.split("/");
        for (var component : components) {
            if (component.equals("..")) {
                stack.pop();
            } else if (!component.isEmpty() && !component.equals(".")) {
                stack.push(component);
            }
        }
        var result = new StringBuilder();
        for (String dir : stack) {
            result.append("/").append(dir);
        }
        return !result.isEmpty() ? result.toString() : "/";
    }

    public static void main(String[] args) {
        Path path = new Path();
        System.out.println(path.simplify("///c/home//.../user/..//usver/"));
    }
}
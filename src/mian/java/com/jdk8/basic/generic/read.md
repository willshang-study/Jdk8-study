## 泛型
    本质：参数化类型
    
## 存在的意义
    1. 通过泛型的语法定义，可以在编译期提供一定的安全检查，过滤掉大部分因类型不符而导致的运行时异常
    2. 提高代码的可读性，本身只是一种语法糖，对JVM运行时性能并没有影响
    
## 分类
    1. 泛型类
    2. 泛型接口
    3. 泛型方法
 
## 类型擦除
    javap -c Test.class 查看JVM字节码，并未有Integer和Number相关信息出现
证明： 1. 泛型只是一种语法糖，在java文件向class文件编译时进行安全检查
       2. class文件中也会保留泛型，但是class文件通过java编译器生成的字节码，并不会保留泛型

## 通配符
* 无界通配符 List<?> 表明该容器中可以放任意的Object
* 上界通配符 List<? extends T> 表明该容器内只能放 T或者T的子类 《更严格》
* 下界通配符 List<? super V>   表明该容器可以放 V或者V的父类  《更宽松》
       
## 扩展
    Window10 注意jdk环境变量配置，javac和javap等指令不能使用了
    1. 定义 JAVA_HOME 变量指向jdk目录
    2. path里面新增两个 
            %JAVA_HOME%\bin （javac是jdk/bin下的一个命令）
            %JAVA_HOME%\jre\bin
    

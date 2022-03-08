<body>
<h1 >JDBC-LoginSystem</h1>
<h2 >前言</h2>
	<div style="text-indent: 25px">
<p >这是我在2021年12月做的的JDBC-Login系统。</p>
<p>当初自学了一段时间Java之后，想着Java能否连接MySql，于是去上网查阅资料，得知Java对接MySql需要JDBC，因此这么一套Login系统便出现了。</p>
<p>这个项目中除了LoginSystem是Login系统的内部文件，其余都是在自学JDBC的过程中创建的测试文件。</p>
<p>如果要运行LoginSystem，需要事先准备好Mysql的jar包（5.x及以上的版本）。</p>
<p>然后用IDEA或是eclipse创建普通工程，导入mysql的jar包，在src下创建LoginSystem文件夹，最后把所有java文件放进去即可。</p>
<p>Login是程序入口，登录root可以做类似操作数据库一样进行查询和增删。</p>
<p>DBUtils是专门连接数据库的类，即内部写好了关于连接的相关操作并通过getConnection()进行返回对象。</p>
<p>DoLogin、Resign、Select以及Delete则是对应Login中的各种操作实现类，在此不一一分析。</p>
	</div>
<h4 >控制台相关指令：</h4>
<p style="text-indent:25px"><code>add</code>：增加用户；<code>select</code>：查询用户；<code>delete</code>：删除用户；<code>exit</code> &amp; <code>quit</code>：退出登录。</p>
<h2 >序</h2>
	<div style="text-indent: 25px">
<p>将Java连接到MySql需要引入一个名为mysql-connector-java.jar的文件，这个文件一般在下载MySql时会自带。</p>
<p>我使用的MySql是8.0.27的版本。</p>
<p>由于这个项目(也许不算一个项目)距离现在也有了一段时间，那个时候遇到了很多很多的问题，比如MySql无法使用root登录遇到了很多错误，但是因为时间太过久远，当时也没有记录。<strong>所以如果在使用该项目时如果遇到问题请自行上网查找。</strong>使用前务必确认MySql能够正常运作。</p>
<p>&nbsp;</p>
	</div>
<h2 >具体使用类</h2>
<div style="margin: 25px">
<p>在引入mysql-connector-java.jar包之后，需要做几个步骤：</p>
<ol>
<li><p>加载驱动(DriverCLass)</p>
</li>
<li><p>获取连接的对象(url)</p>
</li>
<li><p>声明Sql语句</p>
</li>
<li><p>创建处理对象</p>
</li>
<li><p>发送Sql语句到MySql</p>
</li>
<li><p>将MySql查询到的数据返回到Java中</p>
</li>
<li><p>在Java中做输出工作</p>
<p>&nbsp;</p>
</li>

</ol>
	</div>
<h2 >加载驱动</h2>
	<div style="text-indent: 25px">
<p>想要在Java中引用MySql我们就需要使用Java进入MySql。</p>
<p>那么我们就需要先加载MySql的驱动也就是DriverClass。也就是<code>Class.forName(&quot;com.mysql.cj.jdbc.Driver&quot;);</code></p>
<p>如果你的MySql是5.x及之前的版本，那么你的加载类就应该是<code>Class.forName(&quot;com.mysql.jdbc.Driver&quot;);</code></p>
<p>&nbsp;</p>
		</div>
<h2 >获取连接的对象</h2>
	<div style="text-indent: 25px">
<p>MySql加载了类驱动之后，就要开始连接MySql,连接MySql的方法是通过url进行连接。</p>
<p>我们创建一个名为url的字符串&quot;<code>jdbc:mysql://127.0.0.1:3306/xxx</code>&quot;,xxx是你要连接的数据库名称。</p>
<p>然后我们要开始进行连接了。连接要用到一个叫做Connection的类。Connection字面就是连接的意思。</p>
<p>创建类的对象然后将这个对象的指向DriverManager类的getConnection方法。</p>
<p>getConnection()方法的其中一个重写方法中有三个参数分别是url,user,password</p>
<p>url就是我们上一步写的url,user和password则对应的是连接数据库用的用户名密码。</p>
<p>&nbsp;</p>
		</div>
<h2 >声明Sql语句</h2>
	<div style="text-indent: 25px">
<p>前两步我们已经做好了关于连接MySql的基本操作</p>
<p>连接到了数据库，那自然少不了的就是使用MySql语句去对数据库内部的表进行查询以及增删数据。</p>
<p>我们需要创建一个名为sql的字符串，在内部写入sql语句，其中如果是增删的sql语句那么在传值的时候将要传的值用“？”代替。</p>
		</div>
<h2 >创建处理对象 &amp; 发送Sql语句到MySql</h2>
	<div style="text-indent: 25px">
<p>这里用到了两个类：Statement和PreparedStatement。前者是针对不需要传入参数的sql语句进行执行的类，后者则是针对需要传参数的sql语句进行执行的类。</p>
<p>如果你需要查询数据表内的内容，那么创建Statement类是不错的选择。创建它的对象并将其指向之前Connection对象的createStatement()方法。</p>
<p>如果你需要对数据表内的内容进行增删，那么就创建PreparedStatement类对象。创建它的对象指向之前Connection对象的prepareStatement()方法，并在其中传入sql参数，也就是之前也好的sql语句。</p>
<p>针对Statement类对象，我们还需要创建ResultSet类对象将它指向Statement类对象的executeQuery()并在其中传入sql参数，也就是之前也好的sql语句。</p>
<p>&nbsp;</p>
	</div>
<h2 >将MySql查询到的数据返回到Java中 &amp; 在Java中做输出工作</h2>
	<div style="text-indent: 25px">
<p>通过ResultSet类对象，我们可以用rst.next()来判断是否有值传回来，因为next()方法通过源码分析是boolean类型。</p>
<p>如果结果为真，说明rst的确获取到对象，我们就可以通过get方法去将内部值获取，然后用System.out.println进行输出。</p>
<p>&nbsp;</p>
</div>
</body>

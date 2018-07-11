# eXtensible Markup Language(XML)

###1.用处

1. 传输数据（XML，JSON）
2. 配置框架（spring）
3. 设计布局（Android）



有一个root tag，所有的tag都应该在root tag之中。

所有的opening tag 需要对应的closing tag。



tag用来指定你这个数据的类型：

<salary>3000</salary>

层次关系（salary is part of alien, name is part of alien）:

```
<alien>

	<name>Gary</name>

	<salary>3000</salary>

</alien>
```

Rules...

1. one root tag
2. xml is case sensitive
3. every opening tag needs a closing tag
4. every attribute value should be inside a double/single quote

如以下是错的：

```
<alien>

	<name>Gary</name>

	<salary>3000</salary>

</alien>
<alien>

	<name>Tom</name>

	<salary>5000</salary>

</alien>
```

因为有两个root node了。

这样就是对的：

```
<aliens>
    <alien>

        <name>Gary</name>

        <salary>3000</salary>

    </alien>
    <alien>

        <name>Tom</name>

        <salary>5000</salary>

    </alien>
</aliens>
```

只有一个<aliens>的root node.

为了区分两个相同的alien，可以用属性，属性的值需要用引号引起来，如下：

（第一行用来指明这个文件是一个xml文件，版本是1.0，编码方式是UTF-8）

```
<?xml version="1.0" encoding="UTF-8"?>
<aliens>
    <alien aid="A1">
        <name>Gary</name>
        <salary>3000</salary>
    </alien>
    <alien aid="A2">
        <name>Gary</name>
        <salary>3000</salary>
    </alien>
</aliens>
```



### Grammar

structure -> DTD(document type definition)

- deal with the layout of XML（格式--每个element里）
- DTD itself is a XML file

structure，content -> schema

- deal with the data of XML（格式+数据的类型）
- XSD itself is a XML file



#### DTD

比如说以下的格式（第一个alien少了一个<salary>），我希望他报错：

(该文件叫做aliens.xml)

```
<?xml version="1.0" encoding="UTF-8"?>
<aliens>
    <alien aid="A1">
        <name>Gary</name>
        
    </alien>
    <alien aid="A2">
        <name>Gary</name>
        <salary>3000</salary>
    </alien>
</aliens>
```

对应的AlienType.dtd中：

```
<?xml version="1.0" encoding="UTF-8"?>

<!ELEMENT aliens (alien)>
<!ELEMENT alien (name, salary)>
<!ELEMENT name (#PCDATA)>
<!ELEMENT salary (#PCDATA)>
<!ATTLIST alien aid ID #REQUIRED>
<!ATTLIST alien lang (Java|C|Python) "Java">
```

<!ELEMENT>用来声明新的element（XML里面所有的<xxx>...</xxx>都是element）

如`<!ELEMENT aliens (alien)>`表示aliens元素中有一个元素(超过一个会报错)，叫做alien.

若想在aliens元素中含有一个或多个alien元素，则需要使用：`<!ELEMENT aliens (alien+)>`。

如`<!ELEMENT name (PCDATA)>`表示name是类型#PCDATA.

如`<!ATTLIST alien aid ID #REQUIRED>`表示这个attribute属于alien元素，属性叫做aid，是一个unique的属性(类似于SQL中的primary key)，而且必须加上这个属性的（#REQUIRED）

如`<!ATTLIST alien lang (Java|C|Python) "Java">`表示alien有一个属性lang，它只能是Java, C, Python之中的一种，而且默认是“Java”.(可以在XML中不声明，代表是Java)

然后需要在aliens.xml中引用这个.dtd文件：

只要加上一句<!DOCTYPE elementName PUBLIC/SYSTEM>就好了，

格式为： <!DOCTYPE elementName PUBLIC/SYSTEM nameOfYourDTDFile>

如下：

```
<?xml version="1.0" encoding="UTF-8"?>

<!DOCTYPE aliens SYSTEM "alienType.dtd">
<aliens>
    <alien aid="A1">
        <name>Gary</name>
        
    </alien>
    <alien aid="A2">
        <name>Gary</name>
        <salary>3000</salary>
    </alien>
</aliens>
```



#### XSD（XML Schema Definition）

XSD不仅约束了每个element里面有哪些元素，**还约束了每种元素的类型**，以及具体每个元素具体能出现多少次，每个元素的取值范围。



element和type



xmlns -> XML namespace

URI, Prefix

当你的schema保存在Internet上的时候，使用URL来获得资源，保存在本机上使用URI来获得。



类型有两种：complexType和simpleType，前者为复合类型，后者为原子类型。

（可以将DTD类比为一个只知道一个元素有哪些变量的结构体，但是不知道每种变量是哪种结构，而XSD则是一个知道里面有哪些元素，每种元素叫什么，出现多少次，而且元素的类型都是知道的结构体）

如下：

```
<?xml version="1.0" encoding="UTF-8"?>
<schema xmlns="http://www.w3.org/2001/XMLSchema" 
targetNamespace="http://www.example.org/AlienSchema" 
xmlns:tns="http://www.example.org/AlienSchema" 
elementFormDefault="qualified">

<complexType name="alienstype">
	<sequence>
		<element name="alien" type="tns:alientype" maxOccurs="3" minOccurs="1"></element>
	</sequence>
</complexType>

<complexType name="alientype">
	<sequence>
		<element name="name" type="string"></element>
		<element name="salary" type="tns:mySal"></element>
	</sequence>
	<attribute name="aid" type="ID" use="required"></attribute>
</complexType>

<simpleType name="mySal">
	<restriction base="integer">
		<minInclusive value="1000"></minInclusive>
		<maxInclusive value="5000"></maxInclusive>
	</restriction>
</simpleType>
<element name="globalalien" type="tns:alienstype"></element>

</schema>
```

- 定义了两种complexType和一种simpleType
- mySal限制了工资的值必须在1000到5000之间
- tns代表的是target name space（所有在该.xsd文件中定义的类型都需要在引用到的时候加上“tns:“）
- 复合类型中子元素出现的次数可以通过maxOccurs和minOccurs来限定
- 设置属性名
- 定义了根元素的名字和类型

可以通过选择相应的.xsd文件生成符合该.xsd文件限制规则的.xml。
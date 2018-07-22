```
// hard coded (对比下面的variable)
@Path("/messages")
public class MessageResource {
	
	// create a instance of message service
	MessageService messageService = MessageService.getInstance();
	
	@GET
	@Produces(MediaType.APPLICATION_XML)
	// ask Jersey to convert List<Message> to XML --> 
	// Annotate model class(Message) with @XmlRootElement
	public List<Message> getMessage() {
//		return "GET Hello World!";
		return messageService.getAllMessages();  
	}
	
	@GET
	// method的path会被append到class的path之后
	// /{xxx}代表这里从URI传入的是一个variable，无论/message/xxx 传入的是什么，都会被map到messageId这个变量中
	// 只要保证xxx里面没有 ‘/’ （否则就应该map到{xxx}/{yyy}了）
	@Path("/{messageId}")
	@Produces(MediaType.APPLICATION_XML)
	// 以下的注解的意思是，从之前的@Path注解里面拿出的messageId是什么，将其作为参数注入String变量id中
	// 这样我就可以在我的方法里面访问到它
	// 注意到这里的id的类型不必是String, 可以是别的类型，如long， Jersey看到参数需要一个long的类型
	// 就会自动将其转换为long类型
	// 但是如果在URI中输入的不是long类型，这个转换就会失败，会提示404 NOT FOUND
	// 注意： 传入多个参数也可以，做法见笔记
	// 可以使用正则表达式来匹配一个范围内的字符串（具体见文档）
	public Message getMessage(@PathParam("messageId")long id) {
		return messageService.getMessage(id);
//		return "Got path param " + id;
	}
	
}
```


package example;
import java.io.*;

public class ClientInfo implements Serializable
{
	private String id,msg;

	public void setId(String id)
	{
		this.id = id;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
	public String getId()
	{
		return this.id;
	}
	public String getMsg()
	{
		return this.msg;
	}
	//id,msg
}

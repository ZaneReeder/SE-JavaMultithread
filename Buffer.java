
public class Buffer {
	public Message messageBuffer = null;
	public Message responseBuffer = null;
	
	public Buffer() {

	}
	
	public void send(Message message) {
		while(this.messageBuffer != null) { //while message Buffer not empty
			try { wait(); } catch (Exception e) {}
		}
		this.messageBuffer = message;
	}
	
	public void reply(Message message) {
		while(this.responseBuffer != null) {
			try { wait(); } catch (Exception e) {}
		}
		this.responseBuffer = message;
	}
	
	public Message receive() {
		Message msg;
		while(this.messageBuffer == null) {
			try { wait(); } catch (Exception e) {}
		}
		msg = this.messageBuffer;
		this.messageBuffer = null;
		return msg;
	}
	
	public Message getReply() {
		Message rply;
		while(this.responseBuffer == null) {
			try { wait(); } catch (Exception e) {}
		}
		rply = this.responseBuffer;
		this.responseBuffer = null;
		return rply;
	}
}

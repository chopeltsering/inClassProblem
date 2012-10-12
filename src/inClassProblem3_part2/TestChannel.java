package inClassProblem3_part2;

public class TestChannel {

	public static void main(String[] args){
			

        Thread producer, consumer;
        BoundedBuffer buffer;
//        Queue unboundedBuffer;
//        ChannelQueue chQueue;
//        ChannelArray chArray;

        buffer = new BoundedBuffer(5); 
//        unboundedBuffer = new Queue();
//        chQueue = new ChannelQueue(unboundedBuffer);
//        chArray = new ChannelArray(buffer);

        producer = new Thread(new Producer(buffer),"Producer");
        consumer = new Thread(new Consumer(buffer),"Consumer");
        producer.start();
        consumer.start();

	}

}

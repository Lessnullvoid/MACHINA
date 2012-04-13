f=SerialPort.devices[4]

p=ArduinoSMS(f,115200);
p.action={|... msg| msg.postln;	};

fork{10.do{|i|	p.send($w,$d,13,(i+1)%2);	0.1.wait;	}};

p.send($r,$a)
p.close

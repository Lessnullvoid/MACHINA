
SynthDef("sampler", {|	out=0,buffer,gain=0.5,amp=1,rate=1,start=0,pos=0,
			end=1,ffreq=12000,fq=1,rroom=0,rdamp=0,rmix=0.5,play=1,
			eqhifreq=8000,eqmidfreq=1500,eqlofreq=200,
			eqhiq=0.5,eqmidq=0.5,eqloq=0.5,
			eqhidb=0,eqmiddb=0,eqlodb=0	|


	
	var sig,bufFrames,bufpos;
//	bufFrames = BufFrames.kr(buffer);
//	bufpos = Phasor.ar(trig: 0, rate: (play > 0)*BufRateScale.kr(buffer), start: bufFrames*start, end: bufFrames*end);
	
//	bufpos = (start*bufFrames)+((bufpos + (pos*bufFrames)) %  ((end-start)*bufFrames));
	sig = PlayBuf.ar(2, buffer, BufRateScale.kr(buffer)*rate,0,start,1);

//	sig=BufRd.ar(2, buffer, bufpos, 1, 4);
	sig = FreeVerb.ar(sig,rmix,rroom,rdamp);
	sig = MoogFF.ar(sig,ffreq,fq);
	sig = BPeakEQ.ar(sig,eqlofreq,eqloq,eqlodb*6);
	sig = BPeakEQ.ar(sig,eqmidfreq,eqmidq,eqmiddb*6);
	sig = BPeakEQ.ar(sig,eqhifreq,eqhiq,eqhidb*6);
	Out.ar(out,Pan2.ar(sig*amp*(gain*2),0));

/*
wet = BLowShelf.ar(dry, lofreq, 1, band1);
wet = BPeakEQ.ar(wet, midfreq, 1, band2);
wet = BHiShelf.ar(wet, hifreq, 1, band3);
*/
	
//	SendTrig.kr(play <= 0, 0, bufpos/(bufFrames*end));

}).store;


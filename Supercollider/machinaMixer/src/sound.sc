z.sampler=Dictionary.new;
z.amps=Array.fill(2,{ 1 });
z.gains=Array.fill(2,{ 0.5 });

z.addSynth={|env,buf,channel|

	var amp, gain;

	amp = z.amps[channel]; 
	gain= z.gains[channel];

	[amp,gain].postln;

	if(z.sampler[channel]!=nil){
		z.sampler[channel].release;
		z.sampler[channel]=nil;	
	};

	z.sampler.put(
		channel,
		Synth("sampler",
			[\out,channel,\buffer,buf,
			\amp,amp,\gain,gain])
	);
	

};




(
//-------------------------
//main:

c=();

c.negro=Color.new255(35,31,32);
c.amarillo=Color.new255(255,196,37);
c.azul=Color.new255(109,135,153);
c.fondo=Color.black;
c.texto=Color.white;
c.textoB=Color.grey;
c.botonAEstadoA=Color.new255(35,31,32);
c.botonAEstadoB=Color.new255(109,135,153);


c.knobColor=
[
	Color.new255(255,196,37), //rueda
	Color.new255(35,31,32), //palito
	Color.new(1/2,1/2,1/2), //rango
	Color.new(1/2,1/2,1/2), //fondoRango
];

s=Server.local.boot;
v=();
g=();
z=();
m=();
a=();
k=();


g.noModules=4;
g.padding=10;
g.noLists=2;
g.noButtons=4;
g.noKnobs=3;
g.smallKnobSize=1/8;



v.rootPath = "/Users/lessnullvoid/Desktop/machinaMixer/src/";

v.samplesPath = "/Users/lessnullvoid/Desktop/machinaMixer/samples/";

v.samples=List.new;
(v.samplesPath++"*").pathMatch.do{|samples|
v.samples.add(samples.basename)};
v.samples.postln;
v.loadFiles = [
	"model.sc",
	"synthdefs.sc",
	"sound.sc",
	"gui.sc",
	"funciones.sc",
	"arduino.scd"
];


//if(c.resp]]onders.notNil,{c.responders.do{|r|r.remove}});

v.loadFiles.do{|f|  f.basename.postln; thisProcess.interpreter.executeFile(v.rootPath++f);};
)

data1 segment
Vb db 10101b
String db 'Testing'
Vw dw 7
goaddr dd go2
data1 ends

data2 segment
Vd dd 0FbadFFh
data2 ends


code1 segment
assume ds:data1, cs:code1, ss:data2
neg ecx
push eax
popeax
pop Vd[edx+esi*4]
pop goaddr[ebx+esi*2]
jle label1
mov esi, Vd[esi+esi*4]
mov al, Vb[esi+edi*4]
mov ebx,Vd[eax+edx*2]
test ss:Vd[eax+edx*4],ecx
label1:
jmp go1

go2:
nop
nop
nop
jle go2

code1 ends

code2 segment
assume CS:code2, Ds:Data1
go1:
test Vd[esi+edi*2],eax
jmp goaddr
code2 ends

end

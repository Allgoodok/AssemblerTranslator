
data1 segment
Vb db 10101b
String db 'Testing string'
Vw dw 7
goaddr dd go2
data1 ends

data2 segment
Vd dd 0FbadFFh
data2 ends


code1 segment
assume ds:data1 cs:code1, ss:data2
neg ecx
push eax 
jle label1
mov al, Vb[esi+edi*4]
mov ebx,Vd[eax+edx*2]
test ss:Vd[eax+edx*4],ecx
jmp far ptr go1

go2:
nop
nop
nop

label1:

code1 ends

code2 segment

assume CS:code2, Ds:Data
go1:
test Vd[esi+edi*2],eax
jmp goaddr
code2 ends

end

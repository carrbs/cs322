	.align 4
body_print:
!locals=0, max_args=0
	save %sp,-96,%sp
! [CALLST (NAME print) ( (FIELD (PARAM 0) 0))]
	sethi %hi(L$2),%o0
	or %o0, %lo(L$2),%o0
	call printf
	nop
	ret
	restore

	.global main
	.align 4
main:
!locals=1, max_args=1
	save %sp,-96,%sp
! [MOVE (TEMP 1) (CALL (NAME malloc) ( (NAME wSZ)))]

        .section ".text"
        .align 4
        .global scan
scan:
        cmp %i1 , 0 ! compare the input
        bg .LL0     ! continue with function
        ret         ! return
        restore     ! restore main window

.LL0:
        

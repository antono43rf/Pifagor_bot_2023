require: slotfilling/slotFilling.sc
  module = sys.zb-common
theme: /

    state: Start
        q!: $regex</start>
        a: Начнём.

    state: Hello
        intent!: /привет
        a: Привет привет
        
        

<?php
 
$d = '12';
$m = '03';
$y = '1985';
 
function NSum( $s ) {
    preg_match_all( '#([0-9])#' , $s , $r );
    return @(int)array_sum( $r[1] );
}
function W2( $n ) {
    $n = "00" . ((int)$n);
    return $n[strlen($n)-2] . " " . $n[strlen($n)-1] . " ";
}
function NCount( $s , $n ) {
    $n = (string)(int)$n;
    preg_match_all( "#{$n}#" , $s , $r );
    $r = str_repeat($n , count($r[0]));
    if ( !$r ) 
        $r = '-';
    return $r;
}
 
$t1 = NSum($d . $m);
$t2 = NSum($y);
$t3 = $t1 + $t2;
$r1 = $t3;
$r2 = NSum($r1);
$r3 = $r1 - $y[0]*2;
$r4 = NSum($r3);
 
$s = 
    W2($d) . W2($m) . W2($y[0].$y[1]) . W2($y[2].$y[3]) .
    W2($r1) . W2($r2) . W2($r3) . W2($r4);
 
$matrix = array();
for($y=0; $y<3; $y++) {
    for($x=0; $x<3; $x++) {
        $matrix[ $y ][ $x ] = NCount( $s , $x * 3 + $y + 1 );
    }
}
 
 
$wm = array(
    array(
        '%5s(характер,сила воли)   ',    
        '%5s(энергетика,харизма)   ',
        '%5s(познание,творчество)  ',
    ) ,
    array(
        '%5s(здоровье,красота)     ',
        '%5s(логика,интуиция)      ',
        '%5s(трудолюбие,мастерство)',
    ) ,
    array(
        '%5s(удача,везение)        ',
        '%5s(чувство долга)        ',
        '%5s(память,ум)            ',
    )
);
 
echo "<table>\n";
for($y=0; $y<3; $y++) {
    echo " <tr>\n";
    for($x=0; $x<3; $x++) {
        echo 
            "  <td>" .  
            $matrix[ $y ][ $x ] . 
            "<br/>" .
            trim(sprintf( $wm[$x][$y] , '' )) . 
            "</td>\n";
    }
    echo " </tr>\n";
}
echo "</table>";


    state: Bye
        intent!: /пока
        a: Пока пока

    state: NoMatch
        event!: noMatch
        a: Я не понял. Вы сказали: {{$request.query}}

    state: Match
        event!: match
        a: {{$context.intent.answer}}
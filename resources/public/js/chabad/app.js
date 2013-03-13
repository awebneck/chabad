goog.provide('chabad.app');
goog.require('cljs.core');
cljs.core._STAR_print_fn_STAR_ = (function (p1__6110_SHARP_){
return console.log(p1__6110_SHARP_);
});
chabad.app.main = (function main(){
return cljs.core.print.call(null,"Application started!");
});

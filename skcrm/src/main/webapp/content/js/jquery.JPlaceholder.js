/*
 * jQuery placeholder, fix for IE6,7,8,9
 * @author JENA
 * @since 20131115.1504
 * @website ishere.cn
 */
var JPlaceHolder = {
    //妫€娴�
    _check : function(){
        return 'placeholder' in document.createElement('input');
    },
    //鍒濆鍖�
    init : function(){
        if(!this._check()){
            this.fix();
        }
    },
    //淇
    fix : function(){
        jQuery(':input[placeholder]').each(function(index, element) {
            var self = $(this), txt = self.attr('placeholder');
            self.wrap($('<div></div>').css({position:'relative',zoom:'1', border:'none', background:'none', padding:'none', margin:'none',display:'inline'}));
            var pos = self.position(), h = self.outerHeight(true),w = self.outerWidth(true), paddingleft = self.css('padding-left');
            var holder = $('<div class="tip"></div>').text(txt).css({position:'absolute',display:'inline-block',left:pos.left, top:pos.top, width:w,height:h, lineHeight:h+'px', paddingLeft:paddingleft, color:'#aaa'}).appendTo(self.parent());
            holder.css({paddingLeft:'1em' });
            self.focusin(function(e) {
                holder.hide();
            }).focusout(function(e) {
                if(!self.val()){
                    holder.show();
                }
            }).focusout();
            holder.click(function(e) {
                holder.hide();
                self.focus();
            });
        });
    }
};
//鎵ц
jQuery(function(){
    JPlaceHolder.init();   
});
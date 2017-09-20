/*! sprintf.js | Copyright (c) 2007-2013 Alexandru Marasteanu <hello at alexei dot ro> | 3 clause BSD license */(function(e){function r(e){return Object.prototype.toString.call(e).slice(8,-1).toLowerCase()}function i(e,t){for(var n=[];t>0;n[--t]=e);return n.join("")}var t=function(){return t.cache.hasOwnProperty(arguments[0])||(t.cache[arguments[0]]=t.parse(arguments[0])),t.format.call(null,t.cache[arguments[0]],arguments)};t.format=function(e,n){var s=1,o=e.length,u="",a,f=[],l,c,h,p,d,v;for(l=0;l<o;l++){u=r(e[l]);if(u==="string")f.push(e[l]);else if(u==="array"){h=e[l];if(h[2]){a=n[s];for(c=0;c<h[2].length;c++){if(!a.hasOwnProperty(h[2][c]))throw t('[sprintf] property "%s" does not exist',h[2][c]);a=a[h[2][c]]}}else h[1]?a=n[h[1]]:a=n[s++];if(/[^s]/.test(h[8])&&r(a)!="number")throw t("[sprintf] expecting number but found %s",r(a));switch(h[8]){case"b":a=a.toString(2);break;case"c":a=String.fromCharCode(a);break;case"d":a=parseInt(a,10);break;case"e":a=h[7]?a.toExponential(h[7]):a.toExponential();break;case"f":a=h[7]?parseFloat(a).toFixed(h[7]):parseFloat(a);break;case"o":a=a.toString(8);break;case"s":a=(a=String(a))&&h[7]?a.substring(0,h[7]):a;break;case"u":a>>>=0;break;case"x":a=a.toString(16);break;case"X":a=a.toString(16).toUpperCase()}a=/[def]/.test(h[8])&&h[3]&&a>=0?"+"+a:a,d=h[4]?h[4]=="0"?"0":h[4].charAt(1):" ",v=h[6]-String(a).length,p=h[6]?i(d,v):"",f.push(h[5]?a+p:p+a)}}return f.join("")},t.cache={},t.parse=function(e){var t=e,n=[],r=[],i=0;while(t){if((n=/^[^\x25]+/.exec(t))!==null)r.push(n[0]);else if((n=/^\x25{2}/.exec(t))!==null)r.push("%");else{if((n=/^\x25(?:([1-9]\d*)\$|\(([^\)]+)\))?(\+)?(0|'[^$])?(-)?(\d+)?(?:\.(\d+))?([b-fosuxX])/.exec(t))===null)throw"[sprintf] huh?";if(n[2]){i|=1;var s=[],o=n[2],u=[];if((u=/^([a-z_][a-z_\d]*)/i.exec(o))===null)throw"[sprintf] huh?";s.push(u[1]);while((o=o.substring(u[0].length))!=="")if((u=/^\.([a-z_][a-z_\d]*)/i.exec(o))!==null)s.push(u[1]);else{if((u=/^\[(\d+)\]/.exec(o))===null)throw"[sprintf] huh?";s.push(u[1])}n[2]=s}else i|=2;if(i===3)throw"[sprintf] mixing positional and named placeholders is not (yet) supported";r.push(n)}t=t.substring(n[0].length)}return r};var n=function(e,n,r){return r=n.slice(0),r.splice(0,0,e),t.apply(null,r)};e.sprintf=t,e.vsprintf=n})(typeof exports!="undefined"?exports:window);
if (typeof jQuery !== 'undefined') {
    (function($) {
        $('#spinner').ajaxStart(function() {
            $(this).fadeIn();
        }).ajaxStop(function() {
                $(this).fadeOut();
            });
    })(jQuery);
}

$(function() {
    $('textarea[class*=autosize]').autosize({append: "\n"});
    $('[data-rel=tooltip]').tooltip();
    $('[data-rel=popover]').popover({html:true});

    $('.date-range-picker-default').daterangepicker();

    // 澶勭悊榛樿鐨勬棩鏈熻緭鍏ユ鍜屾椂闂磋緭鍏ユ
    $('.date-picker-default').datepicker();
    $('.time-picker-default').timepicker({
        minuteStep: 1,
        showSeconds: false,
        showMeridian: false
    });

    $('.date-picker-grails')
        // 褰撻€夋嫨鏃堕棿鏃讹紝鏇存柊 grails 榛樿鐨勬椂闂磋緭鍏ラ」
        .on('changeDate', function(e) {
            var inputName = this.name.substr(0, this.name.length - 5);
            $('#' + inputName + '_year').val(e.date.getFullYear());
            $('#' + inputName + '_month').val(e.date.getMonth()+1);
            $('#' + inputName + '_day').val(e.date.getDate());
        })
        // 绂佹鍦ㄦ棩鏈熻緭鍏ユ涓緭鍏ュ唴瀹�
        .on('keypress', function(e) {
            return false;
        });

    // 褰撴椂闂磋緭鍏ユ鐨勬椂闂存敼鍙樻椂锛岃嚜鍔ㄦ洿鏂� grails 鐨勬椂闂磋緭鍏ョ┖闂寸殑鍐呭
    $('.time-picker-grails').on('changeTime.timepicker', function(e) {
        var inputName = this.name.substr(0, this.name.length - 5);
        $('#' + inputName + '_hour').val(sprintf("%02d", e.time.hours));
        $('#' + inputName + '_minute').val(sprintf("%02d", e.time.minutes));
    });

    // 涓烘寜閽脊鍑虹‘璁ょ獥鍙�
    $("[data-toggle=confirm]").on('click', function(e) {
        var a = $(this);
        if (!a.data('confirmed')) {
            e.preventDefault();

            var question = a.data('question');
            if (!question) question = 'Are you sure?';

            var yes = a.data('yes');
            if (!yes) yes = 'Yes';

            var no = a.data('no');
            if (!no) no = 'No';

            bootbox.confirm(question, no, yes, function(result) {
                if(result) {
                    a.data('confirmed', 1).simulate('click');
                }
            });
        }
    });

    $(".chzn-select").chosen({
        search_contains: true
    });

    //we could just set the data-provide="tag" of the element inside HTML, but IE8 fails!
    $('input.tag-input').each(function() {
        var tag_input = $(this);
        if(! ( /msie\s*(8|7|6)/.test(navigator.userAgent.toLowerCase())) )
            tag_input.tag({placeholder:tag_input.attr('placeholder')});
        else {
            //display a textarea for old IE, because it doesn't support this plugin or another one I tried!
            tag_input.after('<textarea id="'+tag_input.attr('id')+'" name="'+tag_input.attr('name')+'" rows="3">'+tag_input.val()+'</textarea>').remove();
            //$('#form-field-tags').autosize({append: "\n"});
        }
    });

    // 澶勭悊鑷姩濉厖
    $('input[data-toggle=autocomplete]').each(function() {
        $(this).focus(function() {
            $(this).data('old-value', $(this).val());
        });

        $(this).blur(function() {
            $(this).val($(this).data('old-value'));
        });

        var options = {
            serviceUrl: $(this).data('service-url')
        };

        var t = $(this).data('param-name');
        if (t) options.paramName = t;

        var t = $(this).data('no-cache');
        options.noCache = t == 'true' ? true : false;

        var t = $(this).data('min-chars');
        options.minChars = t ? t : 1;

        var t = $(this).data('auto-select-first');
        options.autoSelectFirst = t == 'true' ? true : false;

        options.onSelect = function (suggestion) {
            var x = $(this).data('real-input');
            $(x).val(suggestion.data);
            $(this).data('old-value', suggestion.value);
        };

        $(this).autocomplete(options);
    });

    // 澶勭悊form鐨勫垹闄ゆ寜閽‘璁�
    // for each form on the page...
    $("form").each(function () {
        var that = $(this); // define context and reference

        /* for each of the submit-inputs - in each of the forms on
         the page - assign click and keypress event */
        $("button", that).bind("click keypress", function ()
        {
            // store the id of the submit-input on it's enclosing form
            that.data("callerId", this.id);
        });
    });

    // assign submit-event to all forms on the page
    $("form").submit(function () {

        /* retrieve the id of the input that was clicked, stored on
         it's enclosing form */
        var that = this;
        var callerId = $(that).data("callerId");

        // determine appropriate action(s)
        if (callerId == "delete-button") {
            $(this).data("callerId", null);
            bootbox.confirm("鎮ㄧ‘瀹氳鎵ц鍒犻櫎鍚楋紵", function(result) {
                if (result) {
                    $('<input>').attr({
                        type: 'hidden',
                        name: '_action_delete',
                        value: 'delete'
                    }).appendTo($(that));
                    that.submit();
                }
            });

            return false;
        }
    });

});

jQuery(function() {
	if(! ('ace' in window) ) window['ace'] = {}
	window['ace'].click_event = $.fn.tap ? "tap" : "click";
	//at some places we try to use 'tap' event instead of 'click' if jquery mobile plugin is available
});

(function($ , undefined) {
	var multiplible = 'multiple' in document.createElement('INPUT');
	var hasFileList = 'FileList' in window;//file list enabled in modern browsers
	var hasFileReader = 'FileReader' in window;

	var Ace_File_Input = function(element , settings) {
		var self = this;
		this.settings = $.extend({}, $.fn.ace_file_input.defaults, settings);

		this.$element = $(element);
		this.element = element;
		this.disabled = false;
		this.can_reset = true;

		this.$element.on('change.ace_inner_call', function(e , ace_inner_call){
			if(ace_inner_call === true) return;//this change event is called from above drop event
			return handle_on_change.call(self);
		});
		
		this.$element.wrap('<div class="ace-file-input" />');
		
		this.apply_settings();
	}
	Ace_File_Input.error = {
		'FILE_LOAD_FAILED' : 1,
		'IMAGE_LOAD_FAILED' : 2,
		'THUMBNAIL_FAILED' : 3
	};


	Ace_File_Input.prototype.apply_settings = function() {
		var self = this;
		var remove_btn = !!this.settings.icon_remove;

		this.multi = this.$element.attr('multiple') && multiplible;
		this.well_style = this.settings.style == 'well';

		if(this.well_style) this.$element.parent().addClass('ace-file-multiple');
		 else this.$element.parent().removeClass('ace-file-multiple');

		this.$element.parent().find(':not(input[type=file])').remove();//remove all except our input, good for when changing settings
		this.$element.after('<label data-title="'+this.settings.btn_choose+'"><span data-title="'+this.settings.no_file+'">'+(this.settings.no_icon ? '<i class="'+this.settings.no_icon+'"></i>' : '')+'</span></label>'+(remove_btn ? '<a class="remove" href="#"><i class="'+this.settings.icon_remove+'"></i></a>' : ''));
		this.$label = this.$element.next();

		this.$label.on('click', function(){//firefox mobile doesn't allow 'tap'!
			if(!this.disabled && !self.element.disabled && !self.$element.attr('readonly')) 
				self.$element.click();
		})

		if(remove_btn) this.$label.next('a').on(ace.click_event, function(){
			if(! self.can_reset ) return false;
			
			var ret = true;
			if(self.settings.before_remove) ret = self.settings.before_remove.call(self.element);
			if(!ret) return false;
			return self.reset_input();
		});


		if(this.settings.droppable && hasFileList) {
			enable_drop_functionality.call(this);
		}
	}

	Ace_File_Input.prototype.show_file_list = function($files) {
		var files = typeof $files === "undefined" ? this.$element.data('ace_input_files') : $files;
		if(!files || files.length == 0) return;

		//////////////////////////////////////////////////////////////////

		if(this.well_style) {
			this.$label.find('span').remove();
			if(!this.settings.btn_change) this.$label.addClass('hide-placeholder');
		}
		this.$label.attr('data-title', this.settings.btn_change).addClass('selected');
		
		for (var i = 0; i < files.length; i++) {
			var filename = typeof files[i] === "string" ? files[i] : $.trim( files[i].name );
			var index = filename.lastIndexOf("\\") + 1;
			if(index == 0)index = filename.lastIndexOf("/") + 1;
			filename = filename.substr(index);
			
			var fileIcon = 'icon-file';
			if((/\.(jpe?g|png|gif|svg|bmp|tiff?)$/i).test(filename)) {
				fileIcon = 'icon-picture';
			}
			else if((/\.(mpe?g|flv|mov|avi|swf|mp4|mkv|webm|wmv|3gp)$/i).test(filename)) fileIcon = 'icon-film';
			else if((/\.(mp3|ogg|wav|wma|amr|aac)$/i).test(filename)) fileIcon = 'icon-music';


			if(!this.well_style) this.$label.find('span').attr({'data-title':filename}).find('[class*="icon-"]').attr('class', fileIcon);
			else {
				this.$label.append('<span data-title="'+filename+'"><i class="'+fileIcon+'"></i></span>');
				var type = $.trim(files[i].type);
				var can_preview = hasFileReader && this.settings.thumbnail 
						&&
						( (type.length > 0 && type.match('image')) || (type.length == 0 && fileIcon == 'icon-picture') )//the second one is for Android's default browser which gives an empty text for file.type
				if(can_preview) {
					var self = this;
					$.when(preview_image.call(this, files[i])).fail(function(result){
						//called on failure to load preview
						if(self.settings.preview_error) self.settings.preview_error.call(self, filename, result.code);
					});
				}
			}

		}

		return true;
	}

	Ace_File_Input.prototype.reset_input = function() {
	  this.$label.attr({'data-title':this.settings.btn_choose, 'class':''})
			.find('span:first').attr({'data-title':this.settings.no_file , 'class':''})
			.find('[class*="icon-"]').attr('class', this.settings.no_icon)
			.prev('img').remove();
			if(!this.settings.no_icon) this.$label.find('[class*="icon-"]').remove();
		
		this.$label.find('span').not(':first').remove();
		
		if(this.$element.data('ace_input_files')) {
			this.$element.removeData('ace_input_files');
			this.$element.removeData('ace_input_method');
		}

		this.reset_input_field();
		
		return false;
	}

	Ace_File_Input.prototype.reset_input_field = function() {
		//http://stackoverflow.com/questions/1043957/clearing-input-type-file-using-jquery/13351234#13351234
		this.$element.wrap('<form>').closest('form').get(0).reset();
		this.$element.unwrap();
	}
	
	Ace_File_Input.prototype.enable_reset = function(can_reset) {
		this.can_reset = can_reset;
	}

	Ace_File_Input.prototype.disable = function() {
		this.disabled = true;
		this.$element.attr('disabled', 'disabled').addClass('disabled');
	}
	Ace_File_Input.prototype.enable = function() {
		this.disabled = false;
		this.$element.removeAttr('disabled').removeClass('disabled');
	}
	
	Ace_File_Input.prototype.files = function() {
		return $(this).data('ace_input_files') || null;
	}
	Ace_File_Input.prototype.method = function() {
		return $(this).data('ace_input_method') || '';
	}
	
	Ace_File_Input.prototype.update_settings = function(new_settings) {
		this.settings = $.extend({}, this.settings, new_settings);
		this.apply_settings();
	}



	var enable_drop_functionality = function() {
		var self = this;
		var dropbox = this.element.parentNode;		
		$(dropbox).on('dragenter', function(e){
			e.preventDefault();
			e.stopPropagation();
		}).on('dragover', function(e){
			e.preventDefault();
			e.stopPropagation();
		}).on('drop', function(e){
			e.preventDefault();
			e.stopPropagation();

			var dt = e.originalEvent.dataTransfer;
			var files = dt.files;
			if(!self.multi && files.length > 1) {//single file upload, but dragged multiple files
				var tmpfiles = [];
				tmpfiles.push(files[0]);
				files = tmpfiles;//keep only first file
			}
			
			var ret = true;
			if(self.settings.before_change) ret = self.settings.before_change.call(self.element, files, true);//true means files have been dropped
			if(!ret || ret.length == 0) {
				return false;
			}
			
			//user can return a modified File Array as result
			if(ret instanceof Array || (hasFileList && ret instanceof FileList)) files = ret;
			
			
			self.$element.data('ace_input_files', files);//save files data to be used later by user
			self.$element.data('ace_input_method', 'drop');


			self.show_file_list(files);
			
			
			self.$element.triggerHandler('change' , [true]);//true means inner_call
			return true;
		});
	}
	
	
	var handle_on_change = function() {
		var ret = true;
		if(this.settings.before_change) ret = this.settings.before_change.call(this.element, this.element.files || [this.element.value]/*make it an array*/, false);//false means files have been selected, not dropped
		if(!ret || ret.length == 0) {
			if(!this.$element.data('ace_input_files')) this.reset_input_field();//if nothing selected before, reset because of the newly unacceptable (ret=false||length=0) selection
			return false;
		}
		

		//user can return a modified File Array as result
		var files = !hasFileList ? null ://for old IE, etc
					((ret instanceof Array || ret instanceof FileList) ? ret : this.element.files);
		this.$element.data('ace_input_method', 'select');


		if(files && files.length > 0) {//html5
			this.$element.data('ace_input_files', files);
		}
		else {
			var name = $.trim( this.element.value );
			if(name && name.length > 0) {
				files = []
				files.push(name);
				this.$element.data('ace_input_files', files);
			}
		}

		if(!files || files.length == 0) return false;
		this.show_file_list(files);

		return true;
	}




	var preview_image = function(file) {
		var self = this;
		var $span = self.$label.find('span:last');//it should be out of onload, otherwise all onloads may target the same span because of delays
		
		var deferred = new $.Deferred
		var reader = new FileReader();
		reader.onload = function (e) {
			$span.prepend("<img class='middle' style='display:none;' />");
			var img = $span.find('img:last').get(0);

			$(img).one('load', function() {
				//if image loaded successfully
				var size = 50;
				if(self.settings.thumbnail == 'large') size = 150;
				else if(self.settings.thumbnail == 'fit') size = $span.width();
				$span.addClass(size > 50 ? 'large' : '');

				var thumb = get_thumbnail(img, size, file.type);
				if(thumb == null) {
					//if making thumbnail fails
					$(this).remove();
					deferred.reject({code:Ace_File_Input.error['THUMBNAIL_FAILED']});
					return;
				}

				var w = thumb.w, h = thumb.h;
				if(self.settings.thumbnail == 'small') {w=h=size;};
				$(img).css({'background-image':'url('+thumb.src+')' , width:w, height:h})									
						.data('thumb', thumb.src)
						.attr({src:'data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAEAAAABCAYAAAAfFcSJAAAADUlEQVQImWNgYGBgAAAABQABh6FO1AAAAABJRU5ErkJggg=='})
						.show()

				///////////////////
				deferred.resolve();
			}).one('error', function() {
				//for example when a file has image extenstion, but format is something else
				$span.find('img').remove();
				deferred.reject({code:Ace_File_Input.error['IMAGE_LOAD_FAILED']});
			});

			img.src = e.target.result;
		}
		reader.onerror = function (e) {
			deferred.reject({code:Ace_File_Input.error['FILE_LOAD_FAILED']});
		}
		reader.readAsDataURL(file);

		return deferred.promise();
	}

	var get_thumbnail = function(img, size, type) {
		
		var w = img.width, h = img.height;
		if(w > size || h > size) {
		  if(w > h) {
			h = parseInt(size/w * h);
			w = size;
		  } else {
			w = parseInt(size/h * w);
			h = size;
		  }
		}

		var dataURL
		try {
			var canvas = document.createElement('canvas');
			canvas.width = w; canvas.height = h;
			var context = canvas.getContext('2d');
			context.drawImage(img, 0, 0, img.width, img.height, 0, 0, w, h);
			dataURL = canvas.toDataURL(/*type == 'image/jpeg' ? type : 'image/png', 10*/)
		} catch(e) {
			dataURL = null;
		}

		//there was only one image that failed in firefox completely randomly! so let's double check it
		if(!( /^data\:image\/(png|jpe?g|gif);base64,[0-9A-Za-z\+\/\=]+$/.test(dataURL)) ) dataURL = null;
		if(! dataURL) return null;

		return {src: dataURL, w:w, h:h};
	}



	///////////////////////////////////////////
	$.fn.ace_file_input = function (option,value) {
		var retval;

		var $set = this.each(function () {
			var $this = $(this);
			var data = $this.data('ace_file_input');
			var options = typeof option === 'object' && option;

			if (!data) $this.data('ace_file_input', (data = new Ace_File_Input(this, options)));
			if (typeof option === 'string') retval = data[option](value);
		});

		return (retval === undefined) ? $set : retval;
	};


	$.fn.ace_file_input.defaults = {
		style:false,
		no_file:'No File ...',
		no_icon:'icon-upload-alt',
		btn_choose:'Choose',
		btn_change:'Change',
		icon_remove:'icon-remove',
		droppable:false,
		thumbnail:false,//large, fit, small
		
		//callbacks
		before_change:null,
		before_remove:null,
		preview_error:null
     }


})(window.jQuery);








(function($ , undefined) {
	$.fn.ace_spinner = function(options) {
		
		//when min is negative, the input maxlength does not account for the extra minus sign
		this.each(function() {
			var icon_up = options.icon_up || 'icon-chevron-up';
			var icon_down = options.icon_down || 'icon-chevron-down';
			
			var btn_up_class = options.btn_up_class || '';
			var btn_down_class = options.btn_down_class || '';
		
			var max = options.max || 999;
			max = (''+max).length;
			var $parent_div = 
				$(this).addClass('spinner-input').css('width' , (max*10)+'px').wrap('<div class="ace-spinner">')
				.after('<div class="spinner-buttons btn-group btn-group-vertical">\
						<button type="button" class="btn spinner-up btn-mini '+btn_up_class+'">\
						<i class="'+icon_up+'"></i>\
						</button>\
						<button type="button" class="btn spinner-down btn-mini '+btn_down_class+'">\
						<i class="'+icon_down+'"></i>\
						</button>\
						</div>')
				.closest('.ace-spinner').spinner(options).wrapInner("<div class='input-append'></div>");

			

			$(this).on('mousewheel DOMMouseScroll', function(event){
				var delta = event.originalEvent.detail < 0 || event.originalEvent.wheelDelta > 0 ? 1 : -1;
				$parent_div.spinner('step', delta > 0);//accepts true or false as second param
				$parent_div.spinner('triggerChangedEvent');
				return false;
			});
			var that = $(this);
			$parent_div.on('changed', function(){
				that.trigger('change');//trigger the input's change event
			});
			
		});
		
		return this;
	}


})(window.jQuery);






(function($ , undefined) {
	$.fn.ace_wizard = function(options) {
		
		this.each(function() {
			var $this = $(this);
			var steps = $this.find('li');
			var numSteps = steps.length;
			var width = parseFloat((100 / numSteps).toFixed(1))+'%';
			steps.css({'min-width':width , 'max-width':width});
			
			$this.show().wizard();

			var buttons = $this.siblings('.wizard-actions').eq(0);
			var $wizard = $this.data('wizard');
			$wizard.$prevBtn.remove();
			$wizard.$nextBtn.remove();
			
			$wizard.$prevBtn = buttons.find('.btn-prev').eq(0).on(ace.click_event,  function(){
				$this.wizard('previous');
			}).attr('disabled', 'disabled');
			$wizard.$nextBtn = buttons.find('.btn-next').eq(0).on(ace.click_event,  function(){
				$this.wizard('next');
			}).removeAttr('disabled');
			$wizard.nextText = $wizard.$nextBtn.text();
		});
		
		return this;
	}


})(window.jQuery);





(function($ , undefined) {
	$.fn.ace_colorpicker = function(options) {
		
		var settings = $.extend( {
			pull_right:false,
			caret:true
        }, options);
		
		this.each(function() {
		
			var $that = $(this);
			var colors = '';
			var color = '';
			$(this).hide().find('option').each(function() {
				var $class = 'colorpick-btn';
				if(this.selected) {
					$class += ' selected';
					color = this.value;
				}
				colors += '<li><a class="'+$class+'" href="#" style="background-color:'+this.value+';" data-color="'+this.value+'"></a></li>';
			}).end().on('change.ace_inner_call', function(){
					$(this).next().find('.btn-colorpicker').css('background-color', this.value);
			})
			.after('<div class="dropdown dropdown-colorpicker"><a data-toggle="dropdown" class="dropdown-toggle" href="#"><span class="btn-colorpicker" style="background-color:'+color+'"></span></a><ul class="dropdown-menu'+(settings.caret? ' dropdown-caret' : '')+(settings.pull_right ? ' pull-right' : '')+'">'+colors+'</ul></div>')
			.next().find('.dropdown-menu').on(ace.click_event, function(e) {
				var a = $(e.target);
				if(!a.is('.colorpick-btn')) return false;
				a.closest('ul').find('.selected').removeClass('selected');
				a.addClass('selected');
				var color = a.data('color');

				$that.val(color).change();

				e.preventDefault();
				return true;//if false, dropdown won't hide!
			});
			
			
		});
		return this;
		
	}	
	
	
})(window.jQuery);












(function($ , undefined) {
	$.fn.ace_tree = function(options) {
		var $options = {
			'open-icon' : 'icon-folder-open',
			'close-icon' : 'icon-folder-close',
			'selectable' : true,
			'selected-icon' : 'icon-ok',
			'unselected-icon' : 'tree-dot'
		}
		
		$options = $.extend({}, $options, options)

		this.each(function() {
			var $this = $(this);
			$this.html('<div class = "tree-folder" style="display:none;">\
				<div class="tree-folder-header">\
					<i class="'+$options['close-icon']+'"></i>\
					<div class="tree-folder-name"></div>\
				</div>\
				<div class="tree-folder-content"></div>\
				<div class="tree-loader" style="display:none"></div>\
			</div>\
			<div class="tree-item" style="display:none;">\
				'+($options['unselected-icon'] == null ? '' : '<i class="'+$options['unselected-icon']+'"></i>')+'\
				<div class="tree-item-name"></div>\
			</div>');
			$this.addClass($options['selectable'] == true ? 'tree-selectable' : 'tree-unselectable');
			
			$this.tree($options);
		});

		return this;
	}


})(window.jQuery);












(function($ , undefined) {
	$.fn.ace_wysiwyg = function($options , undefined) {
		var options = $.extend( {
			speech_button:true,
			wysiwyg:{}
        }, $options);

		var color_values = [
			'#ac725e','#d06b64','#f83a22','#fa573c','#ff7537','#ffad46',
			'#42d692','#16a765','#7bd148','#b3dc6c','#fbe983','#fad165',
			'#92e1c0','#9fe1e7','#9fc6e7','#4986e7','#9a9cff','#b99aff',
			'#c2c2c2','#cabdbf','#cca6ac','#f691b2','#cd74e6','#a47ae2',
			'#444444'
		]

		var button_defaults =
		{
			'font' : {
				values:['Arial', 'Courier', 'Comic Sans MS', 'Helvetica', 'Open Sans', 'Tahoma', 'Verdana'],
				icon:'icon-font',
				title:'Font'
			},
			'fontSize' : {
				values:{5:'Huge', 3:'Normal', 1:'Small'},
				icon:'icon-text-height',
				title:'Font Size'
			},
			'bold' : {
				icon : 'icon-bold',
				title : 'Bold (Ctrl/Cmd+B)'
			},
			'italic' : {
				icon : 'icon-italic',
				title : 'Italic (Ctrl/Cmd+I)'
			},
			'strikethrough' : {
				icon : 'icon-strikethrough',
				title : 'Strikethrough'
			},
			'underline' : {
				icon : 'icon-underline',
				title : 'Underline'
			},
			'insertunorderedlist' : {
				icon : 'icon-list-ul',
				title : 'Bullet list'
			},
			'insertorderedlist' : {
				icon : 'icon-list-ol',
				title : 'Number list'
			},
			'outdent' : {
				icon : 'icon-indent-left',
				title : 'Reduce indent (Shift+Tab)'
			},
			'indent' : {
				icon : 'icon-indent-right',
				title : 'Indent (Tab)'
			},
			'justifyleft' : {
				icon : 'icon-align-left',
				title : 'Align Left (Ctrl/Cmd+L)'
			},
			'justifycenter' : {
				icon : 'icon-align-center',
				title : 'Center (Ctrl/Cmd+E)'
			},
			'justifyright' : {
				icon : 'icon-align-right',
				title : 'Align Right (Ctrl/Cmd+R)'
			},
			'justifyfull' : {
				icon : 'icon-align-justify',
				title : 'Justify (Ctrl/Cmd+J)'
			},
			'createLink' : {
				icon : 'icon-link',
				title : 'Hyperlink',
				button_text : 'Add',
				placeholder : 'URL'
			},
			'unlink' : {
				icon : 'icon-unlink',
				title : 'Remove Hyperlink'
			},
			'insertImage' : {
				icon : 'icon-picture',
				title : 'Insert picture',
				button_text : '<i class="icon-file"></i> Choose Image &hellip;',
				placeholder : 'Image URL'
			},
			'foreColor' : {
				values : color_values,
				title : 'Change Color'
			},
			'backColor' : {
				values : color_values,
				title : 'Change Background Color'
			},
			'undo' : {
				icon : 'icon-undo',
				title : 'Undo (Ctrl/Cmd+Z)'
			},
			'redo' : {
				icon : 'icon-repeat',
				title : 'Redo (Ctrl/Cmd+Y)'
			}
		}
		
		var toolbar_buttons =
		options.toolbar ||
		[
			'font',
			null,
			'fontSize',
			null,
			'bold',
			'italic',
			'strikethrough',
			'underline',
			null,
			'insertunorderedlist',
			'insertorderedlist',
			'outdent',
			'indent',
			null,
			'justifyleft',
			'justifycenter',
			'justifyright',
			'justifyfull',
			null,
			'createLink',
			'unlink',
			null,
			'insertImage',
			null,
			'foreColor',
			null,
			'undo',
			'redo'
		]


		this.each(function() {
			var toolbar = ' <div class="wysiwyg-toolbar btn-toolbar center"> <div class="btn-group"> ';

			for(var tb in toolbar_buttons) if(toolbar_buttons.hasOwnProperty(tb)) {
				var button = toolbar_buttons[tb];
				if(button === null){
					toolbar += ' </div> <div class="btn-group"> ';
					continue;
				}
				
				if(typeof button == "string" && button in button_defaults) {
					button = button_defaults[button];
					button.name = toolbar_buttons[tb];
				} else if(typeof button == "object" && button.name in button_defaults) {
					button = $.extend(button_defaults[button.name] , button);
				}
				else continue;
				
				var className = "className" in button ? button.className : '';
				switch(button.name) {
					case 'font':
						toolbar += ' <a class="btn btn-small '+className+' dropdown-toggle" data-toggle="dropdown" title="'+button.title+'"><i class="'+button.icon+'"></i><i class="icon-angle-down icon-on-right"></i></a> ';
						toolbar += ' <ul class="dropdown-menu dropdown-light">';
						for(var font in button.values)
							if(button.values.hasOwnProperty(font))
								toolbar += ' <li><a data-edit="fontName ' + button.values[font] +'" style="font-family:\''+ button.values[font]  +'\'">'+button.values[font]  + '</a></li> '
						toolbar += ' </ul>';
					break;

					case 'fontSize':
						toolbar += ' <a class="btn btn-small '+className+' dropdown-toggle" data-toggle="dropdown" title="'+button.title+'"><i class="'+button.icon+'"></i>&nbsp;<i class="icon-angle-down icon-on-right"></i></a> ';
						toolbar += ' <ul class="dropdown-menu dropdown-light"> ';
						for(var size in button.values)
							if(button.values.hasOwnProperty(size))
								toolbar += ' <li><a data-edit="fontSize '+size+'"><font size="'+size+'">'+ button.values[size] +'</font></a></li> '
						toolbar += ' </ul> ';
					break;

					case 'createLink':
						toolbar += ' <a class="btn btn-small '+className+' dropdown-toggle" data-toggle="dropdown" title="'+button.title+'"><i class="'+button.icon+'"></i></a> ';
						toolbar += ' <div class="dropdown-menu dropdown-caret input-append pull-right">\
							<input placeholder="'+button.placeholder+'" type="text" data-edit="'+button.name+'" />\
							<button class="btn btn-small btn-primary" type="button">'+button.button_text+'</button>\
						</div> ';
					break;

					case 'insertImage':
						toolbar += ' <a class="btn btn-small '+className+' dropdown-toggle" data-toggle="dropdown" title="'+button.title+'"><i class="'+button.icon+'"></i></a> ';
						toolbar += ' <div class="dropdown-menu dropdown-caret input-append pull-right">\
							<input placeholder="'+button.placeholder+'" type="text" data-edit="'+button.name+'" />\
							<button class="btn btn-small btn-primary" type="button">Insert</button> ';
							if( 'FileReader' in window ) toolbar +=
							 '<div class="center">\
								<button class="btn btn-small btn-success wysiwyg-choose-file" type="button">'+button.button_text+'</button>\
								<input type="file" data-edit="'+button.name+'" />\
							  </div>'
						toolbar += ' </div> ';
					break;

					case 'foreColor':
					case 'backColor':
						toolbar += ' <select class="hide wysiwyg_colorpicker" title="'+button.title+'"> ';
						for(var color in button.values)
							toolbar += ' <option value="'+button.values[color]+'">'+button.values[color]+'</option> ';
						toolbar += ' </select> ';
						toolbar += ' <input style="display:none;" disabled class="hide" type="text" data-edit="'+button.name+'" /> ';
					break;

					default:
						toolbar += ' <a class="btn btn-small '+className+'" data-edit="'+button.name+'" title="'+button.title+'"><i class="'+button.icon+'"></i></a> ';
					break;
				}
			}
			toolbar += ' </div> </div> ';



			//if we have a function to decide where to put the toolbar, then call that
			if(options.toolbar_place) toolbar = options.toolbar_place.call(this, toolbar);
			//otherwise put it just before our DIV
			else toolbar = $(this).before(toolbar).prev();

			toolbar.find('a[title]').tooltip({animation:false});
			toolbar.find('.dropdown-menu input:not([type=file])').on(ace.click_event, function() {return false})
		    .on('change', function() {$(this).closest('.dropdown-menu').siblings('.dropdown-toggle').dropdown('toggle')})
			.on('keydown', function (e) {if(e.which == 27) {this.value='';$(this).change()}});
			toolbar.find('input[type=file]').prev().on(ace.click_event, function (e) { 
				$(this).next().click();
			});
			toolbar.find('.wysiwyg_colorpicker').each(function() {
				$(this).ace_colorpicker({pull_right:true,caret:false}).change(function(){
					$(this).nextAll('input').eq(0).val(this.value).change();
				}).next().find('.btn-colorpicker').tooltip({title: this.title, animation:false})
			});
			
			var speech_input;
			if (options.speech_button && 'onwebkitspeechchange' in (speech_input = document.createElement('input'))) {
				var editorOffset = $(this).offset();
				toolbar.append(speech_input);
				$(speech_input).attr({type:'text', 'data-edit':'inserttext','x-webkit-speech':''}).addClass('wysiwyg-speech-input')
				.css({'position':'absolute'}).offset({top: editorOffset.top, left: editorOffset.left+$(this).innerWidth()-35});
			} else speech_input = null


			var $options = $.extend({}, { activeToolbarClass: 'active' , toolbarSelector : toolbar }, options.wysiwyg || {})
			$(this).wysiwyg( $options );
		});

		return this;
	}


})(window.jQuery);
jQuery(function() {
	//ace.click_event defined in ace-elements.js
	handle_side_menu();

	enable_search_ahead();	

	general_things();//and settings

	widget_boxes();
});



function handle_side_menu() {
	$('#menu-toggler').on(ace.click_event, function() {
		$('#sidebar').toggleClass('display');
		$(this).toggleClass('display');
		return false;
	});
	//mini
	var $minimized = $('#sidebar').hasClass('menu-min');
	$('#sidebar-collapse').on(ace.click_event, function(){
		$('#sidebar').toggleClass('menu-min');
		$(this).find('[class*="icon-"]:eq(0)').toggleClass('icon-double-angle-right');
		
		
		$minimized = $('#sidebar').hasClass('menu-min');
		if($minimized) {
			$('.open > .submenu').removeClass('open');
		}
	});

	var touch = "ontouchend" in document;
	//opening submenu
	$('.nav-list').on(ace.click_event, function(e){

		//check to see if we have clicked on an element which is inside a .dropdown-toggle element?!
		//if so, it means we should toggle a submenu
		var link_element = $(e.target).closest('a');
		if(!link_element || link_element.length == 0) return;//if not clicked inside a link element
		
		if(! link_element.hasClass('dropdown-toggle') ) {//it doesn't have a submenu return
			//just one thing before we return
			//if we are in minimized mode, and we click on a first level menu item
			//and the click is on the icon, not on the menu text then let's cancel event and cancel navigation
			//Good for touch devices, that when the icon is tapped to see the menu text, navigation is cancelled
			//navigation is only done when menu text is tapped
			if($minimized && ace.click_event == "tap" &&
				link_element.get(0).parentNode.parentNode == this /*.nav-list*/ )//i.e. only level-1 links
			{
					var text = link_element.find('.menu-text').get(0);
					if( e.target != text && !$.contains(text , e.target) )//not clicking on the text or its children
					  return false;
			}

			return;
		}
		//
		var sub = link_element.next().get(0);

		//if we are opening this submenu, close all other submenus except the ".active" one
		if(! $(sub).is(':visible') ) {//if not open and visible, let's open it and make it visible
		  var parent_ul = $(sub.parentNode).closest('ul');
		  if($minimized && parent_ul.hasClass('nav-list')) return;
		  
		  parent_ul.find('> .open > .submenu').each(function(){
			//close all other open submenus except for the active one
			if(this != sub && !$(this.parentNode).hasClass('active')) {
				$(this).slideUp(200).parent().removeClass('open');
				
				//uncomment the following line to close all submenus on deeper levels when closing a submenu
				//$(this).find('.open > .submenu').slideUp(0).parent().removeClass('open');
			}
		  });
		} else {
			//uncomment the following line to close all submenus on deeper levels when closing a submenu
			//$(sub).find('.open > .submenu').slideUp(0).parent().removeClass('open');
		}

		if($minimized && $(sub.parentNode.parentNode).hasClass('nav-list')) return false;

		$(sub).slideToggle(200).parent().toggleClass('open');
		return false;
	 })
}


function enable_search_ahead() {
	$('#nav-search-input').typeahead({
		source: ["Alabama","Alaska","Arizona","Arkansas","California","Colorado","Connecticut","Delaware","Florida","Georgia","Hawaii","Idaho","Illinois","Indiana","Iowa","Kansas","Kentucky","Louisiana","Maine","Maryland","Massachusetts","Michigan","Minnesota","Mississippi","Missouri","Montana","Nebraska","Nevada","New Hampshire","New Jersey","New Mexico","New York","North Dakota","North Carolina","Ohio","Oklahoma","Oregon","Pennsylvania","Rhode Island","South Carolina","South Dakota","Tennessee","Texas","Utah","Vermont","Virginia","Washington","West Virginia","Wisconsin","Wyoming"],
		updater:function (item) {
			$('#nav-search-input').focus();
			return item;
		}
	});
}


function general_things() {
 $('.ace-nav [class*="icon-animated-"]').closest('a').on('click', function(){
	var icon = $(this).find('[class*="icon-animated-"]').eq(0);
	var $match = icon.attr('class').match(/icon\-animated\-([\d\w]+)/);
	icon.removeClass($match[0]);
	$(this).off('click');
 });
 
 $('.nav-list .badge[title],.nav-list .label[title]').tooltip({'placement':'right'});



 //simple settings

 $('#ace-settings-btn').on(ace.click_event, function(){
	$(this).toggleClass('open');
	$('#ace-settings-box').toggleClass('open');
 });

 $('#ace-settings-header').removeAttr('checked').on('click', function(){
	if(! this.checked ) {
		if($('#ace-settings-sidebar').get(0).checked) $('#ace-settings-sidebar').click();
	}
	
	$('.navbar').toggleClass('navbar-fixed-top');
	$(document.body).toggleClass('navbar-fixed');
 });
 
 $('#ace-settings-sidebar').removeAttr('checked').on('click', function(){
	if(this.checked) {
		if(! $('#ace-settings-header').get(0).checked) $('#ace-settings-header').click();
	} else {
		if($('#ace-settings-breadcrumbs').get(0).checked) $('#ace-settings-breadcrumbs').click();
	}

	$('#sidebar').toggleClass('fixed');
 });
 
 $('#ace-settings-breadcrumbs').removeAttr('checked').on('click', function(){
	if(this.checked) {
		if(! $('#ace-settings-sidebar').get(0).checked ) $('#ace-settings-sidebar').click();
	}

	$('#breadcrumbs').toggleClass('fixed');
	$(document.body).toggleClass('breadcrumbs-fixed');
 });


 //Switching to RTL (right to left) Mode
 $('#ace-settings-rtl').removeAttr('checked').on('click', function(){
	switch_direction();
 });


 $('#btn-scroll-up').on(ace.click_event, function(){
	var duration = Math.max(100, parseInt($('html').scrollTop() / 3));
	$('html,body').animate({scrollTop: 0}, duration);
	return false;
 });
 
 
  $('#skin-colorpicker').ace_colorpicker().on('change', function(){
	var skin_class = $(this).find('option:selected').data('class');
	
	var body = $(document.body);
	body.removeClass('skin-1 skin-2 skin-3');

	
	if(skin_class != 'default') body.addClass(skin_class);
	
	if(skin_class == 'skin-1') {
		$('.ace-nav > li.grey').addClass('dark');
	}
	else {
		$('.ace-nav > li.grey').removeClass('dark');
	}
	
	if(skin_class == 'skin-2') {
		$('.ace-nav > li').addClass('no-border margin-1');
		$('.ace-nav > li:not(:last-child)').addClass('light-pink').find('> a > [class*="icon-"]').addClass('pink').end().eq(0).find('.badge').addClass('badge-warning');
	}
	else {
		$('.ace-nav > li').removeClass('no-border margin-1');
		$('.ace-nav > li:not(:last-child)').removeClass('light-pink').find('> a > [class*="icon-"]').removeClass('pink').end().eq(0).find('.badge').removeClass('badge-warning');
	}
	
	if(skin_class == 'skin-3') {
		$('.ace-nav > li.grey').addClass('red').find('.badge').addClass('badge-yellow');
	} else {
		$('.ace-nav > li.grey').removeClass('red').find('.badge').removeClass('badge-yellow');
	}
 });
 
}



function widget_boxes() {
	$('.page-content').delegate('.widget-toolbar > [data-action]' , 'click', function(ev) {
		ev.preventDefault();

		var $this = $(this);
		var $action = $this.data('action');
		var $box = $this.closest('.widget-box');

		if($box.hasClass('ui-sortable-helper')) return;

		if($action == 'collapse') {
			var $body = $box.find('.widget-body');
			var $icon = $this.find('[class*=icon-]').eq(0);
			var $match = $icon.attr('class').match(/icon\-(.*)\-(up|down)/);
			var $icon_down = 'icon-'+$match[1]+'-down';
			var $icon_up = 'icon-'+$match[1]+'-up';
			
			var $body_inner = $body.find('.widget-body-inner')
			if($body_inner.length == 0) {
				$body = $body.wrapInner('<div class="widget-body-inner"></div>').find(':first-child').eq(0);
			} else $body = $body_inner.eq(0);


			var expandSpeed   = 300;
			var collapseSpeed = 200;

			if($box.hasClass('collapsed')) {
				if($icon) $icon.addClass($icon_up).removeClass($icon_down);
				$box.removeClass('collapsed');
				$body.slideUp(0 , function(){$body.slideDown(expandSpeed)});
			}
			else {
				if($icon) $icon.addClass($icon_down).removeClass($icon_up);
				$body.slideUp(collapseSpeed, function(){$box.addClass('collapsed')});
			}
		}
		else if($action == 'close') {
			var closeSpeed = parseInt($this.data('close-speed')) || 300;
			$box.hide(closeSpeed , function(){$box.remove()});
		}
		else if($action == 'reload') {
			$this.blur();

			var $remove = false;
			if($box.css('position') == 'static') {$remove = true; $box.addClass('position-relative');}
			$box.append('<div class="widget-box-layer"><i class="icon-spinner icon-spin icon-2x white"></i></div>');
			setTimeout(function(){
				$box.find('.widget-box-layer').remove();
				if($remove) $box.removeClass('position-relative');
			}, parseInt(Math.random() * 1000 + 1000));
		}
		else if($action == 'settings') {

		}

	});
}





function switch_direction() {
	var $body = $(document.body);
	$body
	.toggleClass('rtl')
	//toggle pull-right class on dropdown-menu
	.find('.dropdown-menu:not(.datepicker-dropdown,.colorpicker)').toggleClass('pull-right')
	.end()
	//swap pull-left & pull-right
	.find('.pull-right:not(.dropdown-menu,blockquote,.dropdown-submenu,.profile-skills .pull-right,.control-group .controls > [class*="span"]:first-child)').removeClass('pull-right').addClass('tmp-rtl-pull-right')
	.end()
	.find('.pull-left:not(.dropdown-submenu,.profile-skills .pull-left)').removeClass('pull-left').addClass('pull-right')
	.end()
	.find('.tmp-rtl-pull-right').removeClass('tmp-rtl-pull-right').addClass('pull-left')
	.end()
	
	.find('.chzn-container').toggleClass('chzn-rtl')
	.end()

	.find('.control-group .controls > [class*="span"]:first-child').toggleClass('pull-right')
	.end()
	
	function swap_classes(class1, class2) {
		$body
		 .find('.'+class1).removeClass(class1).addClass('tmp-rtl-'+class1)
		 .end()
		 .find('.'+class2).removeClass(class2).addClass(class1)
		 .end()
		 .find('.tmp-rtl-'+class1).removeClass('tmp-rtl-'+class1).addClass(class2)
	}
	function swap_styles(style1, style2, elements) {
		elements.each(function(){
			var e = $(this);
			var tmp = e.css(style2);
			e.css(style2 , e.css(style1));
			e.css(style1 , tmp);
		});
	}

	swap_classes('align-left', 'align-right');
	swap_classes('arrowed', 'arrowed-right');
	swap_classes('arrowed-in', 'arrowed-in-right');


	//redraw the traffic pie chart on homepage with a different parameter
	var placeholder = $('#piechart-placeholder');
	if(placeholder.size() > 0) {
		var pos = $(document.body).hasClass('rtl') ? 'nw' : 'ne';//draw on north-west or north-east?
		placeholder.data('draw').call(placeholder.get(0) , placeholder, placeholder.data('chart'), pos);
	}
}




$(document).ready(function(){

	var zx = "有疑问咨询0451-58607888";
	
	function check_sfzh(value) {

		var arrExp = [7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2]; //加权因子  
		var arrValid = [1, 0, "X", 9, 8, 7, 6, 5, 4, 3, 2]; //校验码  
		if (/^\d{17}\d|x$/i.test(value)) {
			var sum = 0,
				idx;
			for (var i = 0; i < value.length - 1; i++) {
				// 对前17位数字与权值乘积求和  
				sum += parseInt(value.substr(i, 1), 10) * arrExp[i];
			}
			// 计算模（固定算法）  
			idx = sum % 11;
			// 检验第18为是否与校验码相等  
			return arrValid[idx] == value.substr(17, 1).toUpperCase();
		} else {
			return false;
		}
	}
	
	$('#form').bootstrapValidator({
		excluded: [':disabled'],
		message: '验证失败',
		feedbackIcons: {
			valid: '',
			invalid: '',
			validating: 'fa fa-refresh'
		},
		fields: {
			hc_form_xm: {
				container: '#error-hc_form_xm',
				trigger: 'blur',
				validators: {
					notEmpty: {
						message: '姓名不能为空！' + zx
					},
					stringLength: {
						min: 2,
                        max: 8,
                        message: '姓名长度最小为两位,最大为八位！' + zx
                    },
                    regexp: {
                        regexp: /^[\u4E00-\u9FA5]+$/,
                       // regexp: /^[a-zA-Z0-9_\.]+$/,
			message: '姓名必须为中文,不支持数字与英文'
                    }
				}
			},
			
			agreebbrule: {
				container: '#error-agreebbrule',
				validators: {
					notEmpty: {
						message: '请选择同意！' + zx
					}
				}
			},
			
			hc_form_byxx: {
				container: '#error-hc_form_byxx',
				trigger: 'blur',
				validators: {
					notEmpty: {
						message: '毕业学校不能为空！' + zx
					},
					regexp: {
                        regexp: /^[0-9|\u4E00-\u9FA5]+$/,
                        message: '毕业学校只允许为中文或者数字,不允许为英文或特殊字符'
                    }

				}
			},
			

			hc_form_hkszd: {
				container: '#error-hc_form_hkszd',
				trigger: 'blur',
				validators: {
					notEmpty: {
						message: '户口所在地不能为空！' + zx
					}
				}
			},
			
			hc_form_age: {
				container: '#error-hc_form_age',
				trigger: 'blur',
				validators: {
					notEmpty: {
						message: '年龄不能为空！' + zx
					}
				}
			},
			hc_form_birth: {
				container: '#error-hc_form_birth',
				trigger: 'blur',
				validators: {
					notEmpty: {
						message: '出生日期不能为空！' + zx
					}
				}
			},
			
			hc_form_bylb: {
				container: '#error-hc_form_bylb',
				validators: {
					notEmpty: {
						message: '尚未选择毕业类别！' + zx
					}
				}
			},
			hc_form_zytj: {
				container: '#error-hc_form_zytj',
				validators: {
					notEmpty: {
						message: '尚未选择专业调剂！' + zx
					}
				}
			},
			hc_form_kl: {
				container: '#error-hc_form_kl',
				validators: {
					notEmpty: {
						message: '尚未选择课类！' + zx
					}
				}
			},
			to_cn: {
				container: '#error-hc_form_to_cn',
				validators: {
					notEmpty: {
						message: '尚未选择省份！' + zx
					}
				}
			},
			city: {
				container: '#error-hc_form_to_cn',
				validators: {
					notEmpty: {
						message: '尚未选择城市！' + zx
					}
				}
			},
			
			
			
			
			hc_form_mingzu: {
				container: '#error-hc_form_mingzu',
				validators: {
					notEmpty: {
						message: '尚未选择民族！' + zx
					}
				}
			},
			hc_form_zhengzhimianmao: {
				container: '#error-hc_form_zhengzhimianmao',
				validators: {
					notEmpty: {
						message: '尚未选择政治面貌！' + zx
					}
				}
			},
			
			
			
			hc_form_sfzh: {
				container: '#error-hc_form_sfzh',
				trigger: 'blur',
				validators: {
					callback: {
						message: '填写18位二代身份证号码！' + zx,
						callback: function(value, validator) {
							return check_sfzh(value);
						}
					}
				}
			},
			
	
			
			hc_form_zy1: {
				container: '#error-hc_form_zy1',
				validators: {
					notEmpty: {
						message: '报考专业不能为空！' + zx
					}
				}
			},
			hc_form_jddq_province: {
				container: '#error-hc_form_sfdq',
				validators: {
					notEmpty: {
						message: '希望就读地区省份未选择！' + zx
					}
				}
			},
			hc_form_jddq_city: {
				container: '#error-hc_form_sfdq',
				validators: {
					notEmpty: {
						message: '希望就读地区市县未选择！' + zx
					}
				}
			},
			hc_form_xslx: {
				container: '#error-hc_form_xslx',
				validators: {
					notEmpty: {
						message: '尚未选择学生类型！' + zx
					}
				}
			},
		
			
			hc_form_cj: {
				container: '#error-hc_form_cj',
				validators: {
					notEmpty: {
						message: '尚未选择预计成绩！' + zx
					}
				}
			},
			hc_form_province: {
				container: '#error-hc_form_sfdq',
				validators: {
					notEmpty: {
						message: '地区省份未选择！' + zx
					}
				}
			},
			hc_form_city: {
				container: '#error-hc_form_sfdq',
				validators: {
					notEmpty: {
						message: '地区市县未选择！' + zx
					}
				}
			},
			
			hc_form_dz: {
				container: '#error-hc_form_dz',
				trigger: 'blur',
				validators: {
					notEmpty: {
						message: '街道地址不能为空！' + zx
					},
					stringLength: {
						min: 5,
                        message: '地址太过简单！' + zx
                    }
				}
			},
			hc_form_sj: {
				container: '#error-hc_form_sj',
				trigger: 'blur',
				validators: {
					notEmpty: {
						message: '联系方式不能为空！' + zx
					},
					stringLength: {
						min: 11,
                        max: 11,
                        message: '手机号码长度应为11位！' + zx
                    }
				}
			},
			
			hc_form_jjlxr_fa_name: {
				container: '#error-hc_form_jjlxr_fa_name',
				trigger: 'blur',
				validators: {
					notEmpty: {
						message: '尚未填写紧急联系人姓名！' + zx
					},
					stringLength: {
						min: 2,
                        max: 8,
                        message: '紧急联系人长度最小为两位,最大为八位！' + zx
                    },
                    regexp: {
                        regexp: /^[\u4E00-\u9FA5]+$/,
						message: '紧急联系人必须为中文,不支持数字与英文'
                    }
				}
			},
			hc_form_jjlxr_fa_tel: {
				container: '#error-hc_form_jjlxr_fa_tel',
				trigger: 'blur',
				validators: {
					notEmpty: {
						message: '填写紧急联系人电话不能为空！' + zx
					},
					stringLength: {
						min: 11,
                        max: 11,
                        message: '手机号码长度应为11位！' + zx
                    }
				}
			},
			
			hc_form_qq: {
				container: '#error-hc_form_qq',
				trigger: 'blur',
				validators: {
					integer: {
                        message: 'QQ号码应为数字！' + zx
                    }
				}
			}
		}
	});
	
});

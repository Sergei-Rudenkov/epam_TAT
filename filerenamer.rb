# encoding: utf-8
class Filerenamer
# выводим file_name
attr_accessor :file_name
# конструктор
	def initialize
	puts "Enter please a file name, greater then 50 and less then 200 "
		@file_name = gets.chomp 
		while @file_name.length < 50 
			puts "Name is short, please try another one"
			@file_name = gets.chomp 
		end
	end
	
	def belrus_to_eng_translit(text)
	# "автозамена" lowcase
		translited = text.tr('абвгдеёзийклмнопрстуўфхэыь', 'abvgdeezijklmnoprstuwfhey\'')
	# 	"автозамена" upcase
		translited = translited.tr('АБВГДЕЁЗИЙКЛМНОПРСТУЎФХЭ', 'ABVGDEEZIJKLMNOPRSTUWFHEY\'')
	# замена "вручную" составных букв
		translited = translited.gsub(/[жцчшщъюяЖЦЧШЩЪЮЯ]/,
			'ж' => 'zh', 'ц' => 'ts', 'ч' => 'ch', 'ш' => 'sh', 'щ' => 'sch', 'ъ' => '', 'ю' => 'ju', 'я' => 'ja',
			'Ж' => 'ZH', 'Ц' => 'TS', 'Ч' => 'CH', 'Ш' => 'SH', 'Щ' => 'SCH', 'Ъ' => '', 'Ю' => 'JU', 'Я' => 'JA')
		return translited
	end
	# оставляем только точку перед расширением
	def lastdot(text)
		text.chomp!('.') # избавляемся от точки в конце строки, если такая есть  
		while text.count(".")  > 1		
			text.sub!('.', '_')	#заменяем все точки кроме последней на "_"		
		end
		return text
	end
	def reg_exp(text)
		text.gsub!(/[^a-zA-Z0-9._]/, '_') # все запрещённое превращается в "_"
		return text
	end
	
	def if_big(text)
	if text.length > 200
	   then text.gsub!('_', '') # при переполнение сперва удалим неинформативные "_"
	end   
		while text.length > 200 # если это не помогает начинаем удалять символы слева, с начала имени файла(правильно, конечно бы, удалять справа перед точкой) 
		text[0] = ''
		end
		return text
	end 	
end

pat = Filerenamer.new
result = pat.belrus_to_eng_translit(pat.file_name)
result = pat.reg_exp(result)
result = pat.if_big(result)  
puts pat.lastdot(result)

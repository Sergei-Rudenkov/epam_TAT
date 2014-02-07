class Triangle
	def initialize
		puts 'Hello puts sides of traingle, like: 12,21,12'
		@answer = gets.chomp
		while !its_ok?		
		puts 'Wrong syblos is exist in the string, please repiat:'
			@answer = gets.chomp
		end
	end

	def its_triang? 
		arr = @answer.split(',')
		if arr[0].to_i >= arr[1].to_i + arr[2].to_i || arr[2].to_i >= arr[1].to_i + arr[0].to_i || arr[1].to_i >= arr[2].to_i + arr[0].to_i 
		then return false
		else return true
		end
	end
	
	def whats_kind
		arr = @answer.split(',')
		a = arr[0].to_i
		b = arr[1].to_i
		c = arr[2].to_i 
		if a == b && b == c
		then puts 'Its equilateral triangle'
		end
		if a == b || b == c || a == c
		then puts 'Its isosceles triangle'
		end
		if a**2 == b**2 + c**2 || b**2 == a** + c**2 || c**2 == b**2 + a**2
		then puts 'Its rectangular triangle'
		end
	end	
	
private
	def its_ok? 
		if	@answer.scan(/[^0-9,]/).size == 0 && @answer.split(',').size == 3
		then return true
		else return false
		end
	end 
end
tr = Triangle.new
if tr.its_triang?
	then tr.whats_kind		
	else puts 'Its not a triangle, sorry!'
end

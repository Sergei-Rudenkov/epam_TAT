class Tictac
	def initialize
		@field = Array.new(9,'- ')
		   @symbol = '0 '
  end

  def who_move
  @count = @count.to_i + 1
    if @count % 2 == 0
      then
      return human_move
    else
      return comp_move
    end
  end
  
  def human_move
  answer = nil
  @symbol = '+ '
    while !possible?(answer) do
      puts 'Make move, put number 0..8'
      answer = gets.chomp
    end
    if answer.to_i
	    then
	@field[answer.to_i] = @symbol
	return @field
    end
  end
  
  def comp_move
	answer = nil
	@symbol = '0 '
	while !possible?(answer) do
		answer = rand(9)
	end
	if answer.to_i
	    then
	@field[answer.to_i] = @symbol
	return @field
	
  end
end

  def possible? answer
      if answer && @field[answer.to_i] == '- '
        then return true
        else return false
      end
  end
  
  def show
	print @field[0..2]
	puts ''
	print @field[3..5]
	puts ''
	print @field[6..9]
	puts ' '
	puts ' '
  end
  
  def some_one_win
	
	if @field[0] == @field[1] && @field[1] == @field[2] && @field[1] != '- '
	then return puts 'Wins owner of a ' + @symbol
	end
	if @field[3] == @field[4] && @field[4] == @field[5] && @field[5] != '- '
	then return puts 'Wins owner of a ' + @symbol
	end
	if @field[6] == @field[7] && @field[7] == @field[8] && @field[8] != '- '
	then return puts 'Wins owner of a ' + @symbol
	end
	if @field[0] == @field[3] && @field[3] == @field[6] && @field[6] != '- '
	then return puts 'Wins owner of a ' + @symbol
	end
	if @field[1] == @field[4] && @field[1] == @field[7] && @field[1] != '- '
	then return puts 'Wins owner of a ' + @symbol
	end
	if @field[2] == @field[5] && @field[5] == @field[8] && @field[8] != '- '
	then return puts 'Wins owner of a ' + @symbol
	end
	if @field[0] == @field[4] && @field[4] == @field[8] && @field[8] != '- '
	then return puts 'Wins owner of a ' + @symbol
	end
	if @field[2] == @field[4] && @field[4] == @field[6] && @field[6] != '- '
	return puts 'Wins owner of a ' + @symbol
	else 
	return who_move
	end
  end

end
ttt = Tictac.new
9.times do
ttt.some_one_win
ttt.show
end


# Testing code
import textwrap 
#-----------------------------------------------
# A Global string representing a sentence
value = """This function wraps the input paragraph such that each line 
in the paragraph is at most width characters long. The wrap method returns a list of output lines. The returned list 
is empty if the wrapped output has no content."""

def text_wrap(width):
    """
        A function that wraps sentences based on a limit using built-in python
        functions from the textWrap module.

        Args:
            width: The limit given for the sentence.

        Returns:
            word_list: A list of the sentences after being wrapped.
    """
    
    # Wrap this text. 
    wrapper = textwrap.TextWrapper(width) # Accepts the limit

    word_list = wrapper.wrap(value) # Wraps the Global string: value

    # Print each line. 
    for element in word_list: 
        print(element)

    return word_list
#-----------------------------------------------
##def auto_wrap_unfin(string, limit):
##
##    s_length = len(string)
##    word_list = list()
##
##    for x in range(s_length):
##        if string[x] == " ":
##            cut_off = x
##        if (x == limit) and (string[x] != " "):
##            # TODO: Use a loop to find the beginning of the word
##            
##            # This function is incomplete
##            # I found this method tedious, hence I moved on
##            continue
##    
##    return word_list
#-----------------------------------------------
def auto_wrap(string, limit):
    """
        A function that wraps sentences based on a limit.

        Args:
            string: This represents the sentence to wrap.
            limit: The character limit given for the sentence.

        Returns:
            word_list: A list of the sentences after being wrapped.
    """

    # Adds an 'Ending' to the string
    string += " $#END"
    all_words = string.split() # Splits the string at white spaces    

    sentence = ""
    word_length = len(sentence)
    sentence_list = list()

    for word in all_words:
        
        # Adds the final sentence to list of words
        if word == "$#END":
            #sentence = sentence[:-6]
            sentence_list.append(sentence)
            break
            
        word_length += len(word)
        
        if word_length <= limit:
            sentence += (word + " ")
            word_length += 1
        elif word_length > limit:
            sentence_list.append(sentence)
            sentence = (word + " ")
            word_length = len(sentence)

        # Adds the final sentence to the list of sentences
        if word == "$#END":
            sentence = sentence[:-6]
            sentence_list.append(sentence)

    # Prints each line.
    for num in range( len(sentence_list) ): 
        print(f"{num+1}) {sentence_list[num]}")
    
    return sentence_list
#-----------------------------------------------
def locate_rivers(line_list):
    """
        A function that locates the longest typography river and
        replaces the river in the strings/sentences with asteriks.

        Args:
            line_list: A list of strings/sentences.

        Returns:
            line_list: A list of the sentences after replacing all the typography
            rivers with asteriks.
    """

    space_list = list()
    potential_r = list()
    length = len(line_list)

    # This block of code creates a list of index numbers,
    # identifying all the white spaces.
    for line in line_list:
        line = line.strip()
        lst = [ x for x in range( len(line) ) if line[x] == " " ]
        space_list.append(lst)

    # This block of code loops through the index numbers,
    # then checks for potential rivers in the following list
    for a in range(length):

        for index in (space_list[a]):
            spot = index
            lst = [[a],spot] # Keeps track the the line number and index numbers.

            # This assumes a river will be 1 less than the previous river location,
            # equal to the previous location or 1 above the previous location.
            for index_lst in (space_list[a+1:]):
                if (spot-1 in index_lst):
                    spot -= 1
                    lst.append(spot)
                elif (spot in index_lst):
                    lst.append(spot)
                elif (spot+1 in index_lst):
                    spot += 1
                    lst.append(spot)
                else:
                    break
            
            potential_r.append(lst)

    # Calculates the length of the longest river.
    rivers = list()
    river_length = max(map(len, potential_r))

    # Creates a list of the river locations.
    for lst in potential_r:
        if len(lst) == river_length:
            rivers.append(lst)

    #first_river = max(potential_r, key = len)
    #print(f"The length of the longest river is {river_length}.")
    #print(f"The first longest river is: {first_river}.")

    # Replaces the River whitespace with "*".
    for river_location in rivers:
        for num in range(length):
            if num == river_location[0][0]:
                for index in (river_location[1:]):
                    line_list[num] = line_list[num][:index] + "*" + line_list[num][index+1:]
                    num += 1
                break

    # Prints each line.
    for num in range( length ): 
        print(f"{num+1}) {line_list[num]}")

    return line_list

#-----------------------------------------------
if __name__ == "__main__":

    print("* 1) A function to auto-wrap sentences.")
    print("* 2) A function to identify \'Rivers\' in auto-wrapped sentences.")

    sentence = input("\n* Enter a sentence.\n - ")
    limit = int( input("* Enter a limit: ") )

    print("\nThe wrapped sentence is:")

    # Calls the auto-wrap function
    wrapped = auto_wrap(sentence, limit)

    print("\nThe \'Rivers\' in the sentence is:")

    # Calls the locate river function
    rivers = locate_rivers(wrapped)



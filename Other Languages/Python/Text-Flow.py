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
def auto_wrap_unfin(string, limit):

    s_length = len(string)
    word_list = list()

    for x in range(s_length):
        if string[x] == " ":
            cut_off = x
        if (x == limit) and (string[x] != " "):
            # TODO: Use a loop to find the beginning of the word
            
            # This function is incomplete
            # I found this method tedious, hence I moved on
            continue
    
    return word_list
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
    word_list = list()

    for word in all_words:
            
        word_length += len(word)
        
        if word_length <= limit:
            sentence += (word + " ")
            word_length += 1
        elif word_length > limit:
            word_list.append(sentence)
            sentence = (word + " ")
            word_length = len(sentence)

        # Adds the final sentence to list of words
        if word == "$#END":
            sentence = sentence[:-6]
            word_list.append(sentence)

    # Prints each line. 
    for num in range( len(word_list) ): 
        print(f"{num+1}) {word_list[num]}")
    
    return word_list
#-----------------------------------------------
def locate_rivers(lst):
    pass

#-----------------------------------------------
##if __name__ == "__main__":
##
##    print("* 1) A function to auto-wrap sentences.")
##    print("* 2) A function to identify \'Rivers\' in auto-wrapped sentences.")
##
##    sentence = input("\n* Enter a sentence.\n - ")
##
##    print("The wrapped sentence is:")
##
##    # Calls the auto-wrap function
##    wrapped = auto_wrap(sentence)
##
##    print("The \'Rivers\' in the sentence is:")
##
##    # Calls the locate river function
##    rivers = locate_rivers(wrapped)

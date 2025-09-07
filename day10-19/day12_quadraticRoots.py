import cmath 
from fractions import Fraction

def main():
    
    print("Quadratic Solver: ax² + bx + c = 0\n")   # Display program header
    
    # Take input for coefficients
    a = int(input("Enter coefficient a: "))
    b = int(input("Enter coefficient b: "))
    c = int(input("Enter coefficient c: "))

    # Safeguard: if a = 0, it’s not quadratic
    if a == 0:
        print("\nThis is not a quadratic equation.")
        return

    # Calculate discriminant and solve equation
    d = discriminant(a,b,c)
    quadraticFormula(a,b,d) 
    

def discriminant(a,b,c):
    return b**2 - (4 * a * c)   # Formula: b^2 - 4ac
     

def formatNumber(x):
    # Case 1: The solution is a real number
    if x.imag == 0:  

        # Convert real part to a simplified fraction
        frac = Fraction(x.real).limit_denominator()
        return str(frac) 
    
    # Case 2: The solution is complex
    return f"{x}".replace("j", "i") # Replace Python's "j" with the math-style "i"


def quadraticFormula(a,b,d):
    # Apply quadratic formula: (-b ± √d) / 2a
    x1 = (-b + cmath.sqrt(d)) / (2 * a)
    x2 = (-b - cmath.sqrt(d)) / (2 * a)
    
    # Ensure x1 is always the smaller root
    if x1.real > x2.real:
        x1, x2 = x2, x1

    # Print results in simplified form
    print("\nResults:")
    print("x1 =", formatNumber(x1))
    print("x2 =", formatNumber(x2))


if __name__ == '__main__':
    main()  # Run the program starting at main()
/*
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package net.sf.jclal.util.random;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * <p>RandEcu is an advanced multiplicative linear congruential random number
 * generator with a period of aproximately 10<SUP>18</SUP>. RandEcu is a direct
 * translation from Fortran of the <B>RANECU</B> subroutine published in the
 * paper</p>
 *
 * <p>F. James, <CITE>Comp. Phys. Comm.</CITE> <STRONG>60</STRONG> (1990) p329-344
 * </p>
 * <p>The algorithm was originally described in
 * </p>
 * <p>P. L'Ecuyer, <CITE>Commun. ACM.</CITE> <STRONG>1988</STRONG> (1988) p 742
 * </p>
 *
 */
public class Ranecu extends AbstractRandGen {
    /////////////////////////////////////////////////////////////////
    // --------------------------------------- Serialization constant
    /////////////////////////////////////////////////////////////////

    /**
     * Generated by Eclipse
     */
    private static final long serialVersionUID = -6085545562116115737L;

    /////////////////////////////////////////////////////////////////
    // --------------------------------------------------- Attributes
    /////////////////////////////////////////////////////////////////
    /**
     * First seed
     */
    private int seed1;

    /**
     * Second seed
     */
    private int seed2;

    /////////////////////////////////////////////////////////////////
    // ------------------------------------------------- Constructors
    /////////////////////////////////////////////////////////////////
    /**
     * Empty constructor
     */
    protected Ranecu() {
        super();
    }

    /**
     * Default constructor (used by RandgenFactory).
     *
     * @param seed1 First seed
     * @param seed2 Second seed
     */
    public Ranecu(int seed1, int seed2) {
        super();
        this.seed1 = seed1;
        this.seed2 = seed2;
    }

    /////////////////////////////////////////////////////////////////
    // -------------------------- Overwriting Rangen abstract methods
    /////////////////////////////////////////////////////////////////
    // IRanGen interface
    /**
     * {@inheritDoc}
     *
     * @return A random number.
     */
    @Override
    public double raw() {
        int k, iz;
        k = seed1 / 53668;
        seed1 = 40014 * (seed1 - k * 53668) - k * 12211;
        if (seed1 < 0) {
            seed1 = seed1 + 2147483563;
        }
        k = seed2 / 52774;
        seed2 = 40692 * (seed2 - k * 52774) - k * 3791;
        if (seed2 < 0) {
            seed2 = seed2 + 2147483399;
        }
        iz = seed1 - seed2;
        if (iz < 1) {
            iz = iz + 2147483562;
        }
        return (iz * 4.656613e-10);
    }

    /**
     * {@inheritDoc}
     *
     * This method has been overwrited to improve performance.
     *
     * @param d d param
     * @param n n param
     */
    @Override
    public final void raw(double d[], int n) {
        for (int i = 0; i < n; i++) {
            int k, iz;
            k = seed1 / 53668;
            seed1 = 40014 * (seed1 - k * 53668) - k * 12211;
            if (seed1 < 0) {
                seed1 = seed1 + 2147483563;
            }
            k = seed2 / 52774;
            seed2 = 40692 * (seed2 - k * 52774) - k * 3791;
            if (seed2 < 0) {
                seed2 = seed2 + 2147483399;
            }
            iz = seed1 - seed2;
            if (iz < 1) {
                iz = iz + 2147483562;
            }
            d[i] = iz * 4.656613e-10;
        }
    }

    // java.lang.Object methods
    /**
     * {@inheritDoc}
     *
     * @return A string information of the class.
     */
    @Override
    public String toString() {
        ToStringBuilder tsb = new ToStringBuilder(this);
        // Append genotype
        tsb.append("seed1", seed1);
        // Append phenotype
        tsb.append("seed2", seed2);
        // Return generated string
        return tsb.toString();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.seed1;
        hash = 11 * hash + this.seed2;
        return hash;
    }

    /**
     * {@inheritDoc}
     *
     * @param other The object to compare
     * @return If the objects are equals.
     */
    @Override
    public boolean equals(Object other) {
        // Is other is an instance of Ranecu
        if (other instanceof Ranecu) {
            // Type conversion
            Ranecu o = (Ranecu) other;
            // Performs equallity test
            EqualsBuilder eb = new EqualsBuilder();
            // Append genotype
            eb.append(seed1, o.seed1);
            // Append phenotype
            eb.append(seed2, o.seed2);
            // Returns result
            return eb.isEquals();
        } // Else, return false
        else {
            return false;
        }
    }
}
